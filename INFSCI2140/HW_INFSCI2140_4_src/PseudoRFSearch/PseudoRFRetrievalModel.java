package PseudoRFSearch;

import java.util.*;

import Classes.Document;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import SearchLucene.*;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class PseudoRFRetrievalModel {

	private MyIndexReader ixreader;
	private QueryRetrievalModel queryRetrievalModel;
	private long collectionLength;
	private double mu = 2000.0;

	public PseudoRFRetrievalModel(MyIndexReader ixreader) throws Exception {
		this.ixreader = ixreader;
		this.queryRetrievalModel = new QueryRetrievalModel(ixreader);
		this.collectionLength = this.ixreader.collectionLength(); // for calculating P(token|ref)
	}

	/**
	 * Search for the topic with pseudo relevance feedback in 2020 Fall assignment 4.
	 * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
	 *
	 * @param aQuery The query to be searched for.
	 * @param TopN The maximum number of returned document
	 * @param TopK The count of feedback documents
	 * @param alpha parameter of relevance feedback model
	 * @return TopN most relevant document, in List structure
	 */
	public List<Document> RetrieveQuery( Query aQuery, int TopN, int TopK, double alpha ) throws Exception {
		/* 
		 * this method will return the retrieval result of the given Query, and this result is enhanced with pseudo relevance feedback
		 * (1) you should first use the original retrieval model to get TopK documents, which will be regarded as feedback documents
		 * (2) implement GetTokenRFScore to get each query token's P(token|feedback model) in feedback documents
		 * (3) implement the relevance feedback model for each token: combine the each query token's original retrieval score P(token|document) with its score in feedback documents P(token|feedback model)
		 * (4) for each document, use the query likelihood language model to get the whole query's new score, P(Q|document)=P(token_1|document')*P(token_2|document')*...*P(token_n|document')
		 * (5) sort all retrieved documents from most relevant to least, and return TopN
		 */

		/* part (1) & (2) */
		// get HashMap of P(token|feedbackDocs)
		HashMap<String, Double> TokenRFScore = GetTokenRFScore(aQuery, TopK);

		// [THOUGHTS]
		// store all info of token locally upon calling ixreader:
		// (*) keep records of needed docid in docIds
		// (a) calculate single doc score by given token, using Dirichlet prior smoothing:
		//     P(token|D) = ( C(token, D) + mu*P(token|ref) ) / ( |D| + mu )
		// (b) calculate modified single doc score by given token, using relevant feedback model:
		//     P'(token|D) = alpha*P(token|D) + (1-alpha)*P(token|feedbackDocs)
		// (c) store in tokenDocScores: <qt, <docid, score>>
		// (d) calculate total doc score by given query
		//     P(Q|D) = P(token1|D)*P(token2|D)*...
		// (e) store in documentList
		// (f) sort documentList in descending order

		/* part (3) */
		Map<String, Map<Integer, Double>> tokenDocScores = new HashMap<>();
		Set<Integer> docIds = new HashSet<>();

		String[] queryTokens = aQuery.GetQueryContent().split(" ");
		for (String qt : queryTokens) {
			long collectionFreq = ixreader.CollectionFreq(qt); // for calculating P(qt|ref)

			// note that token might not appear in the collection
			if (collectionFreq != 0) {
				long collectionTokenFreq = collectionFreq/collectionLength; // P(qt|ref)

				// docScores: <docid, score>
				Map<Integer, Double> docScores = new HashMap<>();

				int[][] posting = ixreader.getPostingList(qt);
				for (int[] p : posting) {
					int docid = p[0];
					int docTokenFreq = p[1]; // C(qt, D)
					int docLength = ixreader.docLength(docid); // |D|

					// (*) keep records of needed docid
					docIds.add(docid);
					// (a) calculate P(token|D)
					double tokenDocScore = (docTokenFreq + mu*collectionTokenFreq)/(docLength + mu);
					// (b) calculate P'(token|D)
					double tokenDocScoreNew = (alpha*tokenDocScore + (1-alpha)*TokenRFScore.get(qt));
					docScores.put(docid, tokenDocScoreNew);
				}
				// (c) store in tokenDocScores
				tokenDocScores.put(qt, docScores);
			}
		}

		/* part (4) */
		List<Document> documentList = new ArrayList<>();
		for (int docid : docIds) {
			// (d) calculate P(Q|D)
			double docScore = 1.0;
			for (String qt : queryTokens) {
				if (tokenDocScores.containsKey(qt)) {
					Map<Integer, Double> docScores = tokenDocScores.get(qt);
					if (docScores.containsKey(docid)) docScore *= docScores.get(docid);
				}
			}

			// (e) store in documentList
			String docno = ixreader.getDocno(docid);
			Document document = new Document(Integer.toString(docid), docno, docScore);
			documentList.add(document);
		}

		/* part (5) */
		// (f) sort documentList in descending order
		documentList.sort((d1, d2) -> Double.compare(d2.score(), d1.score()));

		// return results
		List<Document> results = new ArrayList<>();
		for (int i = 0; i < TopN; i++) {
			results.add(documentList.get(i));
		}
		return results;
	}

	public HashMap<String,Double> GetTokenRFScore( Query aQuery, int TopK ) throws Exception {
		/* 
		 * for each token in the query, you should calculate token's score in feedback documents:
		 * P(token|feedback documents) use Dirichlet smoothing
		 * save <token, score> in HashMap TokenRFScore, and return it
		 */

		HashMap<String, Double> TokenRFScore = new HashMap<String, Double>();

		// retrieve TopK relevant documents
		List<Document> relevantDocList = this.queryRetrievalModel.retrieveQuery(aQuery, TopK);

		// store the retrieved docid in relevantDocid (ArrayList)
		List<Integer> relevantDocid = new ArrayList<>();
		if (relevantDocList != null) {
			for (Document rd : relevantDocList) {
				relevantDocid.add(Integer.parseInt(rd.docid()));
			}
		}

		// calculate token's score in feedbackDocs, using Dirichlet prior smoothing:
		// P(token|feedbackDocs) = ( C(token, feedbackDocs) + mu*P(token|ref) ) / ( |feedbackDocs| + mu )
		String[] queryTokens = aQuery.GetQueryContent().split(" ");
		for (String qt : queryTokens) {
			long collectionFreq = this.ixreader.CollectionFreq(qt); // for calculating P(token|ref)

			// note that token might not appear in the collection
			if (collectionFreq == 0) {
				TokenRFScore.put(qt, 1.0);
			} else {
				long collectionTokenFreq = collectionFreq/this.collectionLength; // P(token|ref)

				int feedbackDocLength = 0; // |feedbackDocs|
				int feedbackDocTokenFreq = 0; // C(token, feedbackDocs)

				// treat all feedbackDocs as one big pseudo document
				for (int i = 0; i < relevantDocid.size(); i++) {
					int docid = relevantDocid.get(i);
					feedbackDocLength += this.ixreader.docLength(docid);
					feedbackDocTokenFreq += this.ixreader.TokenFreq(qt, docid);
				}

				TokenRFScore.put(qt, (feedbackDocTokenFreq + this.mu*collectionTokenFreq)/(feedbackDocLength + this.mu));
			}
		}

		return TokenRFScore;
	}

}
