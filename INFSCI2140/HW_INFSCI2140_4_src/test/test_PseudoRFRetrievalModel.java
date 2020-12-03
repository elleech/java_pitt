/**
 * This is for testing the class of PseudoRFRetrievalModel
 * YEC24@pitt.edu
 */

package test;

import java.util.*;

import Classes.Document;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import PseudoRFSearch.PseudoRFRetrievalModel;
import SearchLucene.ExtractQuery;
import SearchLucene.QueryRetrievalModel;

public class test_PseudoRFRetrievalModel {

//	/*
	public static void main(String[] args) throws Exception {
		MyIndexReader ixreader = new MyIndexReader("trectext");
		PseudoRFRetrievalModel PRFSearchModel = new PseudoRFRetrievalModel(ixreader);
		ExtractQuery queries = new ExtractQuery();

		long startTime = System.currentTimeMillis();
		while (queries.hasNext()) {
			Query aQuery = queries.next();
			List<Document> results = PRFSearchModel.RetrieveQuery(aQuery, 5, 50, 0.4);
			if (results != null) {
				int rank = 1;
				for (Document result : results) {
					System.out.println(aQuery.GetTopicId() + " Q0 " + result.docno() + " " + rank + " "
							+ result.score() + " MYRUN");
					rank++;
				}
			}
		}
		long endTime = System.currentTimeMillis(); 

		// output running time
		System.out.println("\n\n4 queries search time: " + (endTime - startTime) / 60000.0 + " min");
		ixreader.close();
	}
//	 */

	/* [TRYOUT] QueryRetrievalModel
	private static MyIndexReader ixreader;
	private static QueryRetrievalModel queryRetrievalModel;
	private static long collectionLength;
	private static double mu = 2000.0;

	private static int TopN = 20;
	private static int TopK = 100;
	private static double alpha = 0.4;

	public static void main(String[] args) throws Exception {
		// need to use HW2MainLucene.java to generate indexes first
		// or directly copy the data folder from HW3
		ixreader = new MyIndexReader("trectext");
		queryRetrievalModel = new QueryRetrievalModel(ixreader);
		collectionLength = ixreader.collectionLength(); // for calculating P(token|ref)

		ExtractQuery queries = new ExtractQuery();

		int n = 0;
		if (n < 4) for (int i = 0; i < n; i++) queries.next();
		Query aQuery = queries.next();
		System.out.println(aQuery.GetTopicId() + " " + aQuery.GetQueryContent());

		// my approach from HW3 is too slow. need to reduce the times of calling ixreader!
		List<Document> results = RetrieveQuery(aQuery, TopN, TopK, alpha);

		ixreader.close();
	}

	public static List<Document> RetrieveQuery( Query aQuery, int TopN, int TopK, double alpha ) throws Exception {
		long startTokenRFScoreTime = System.currentTimeMillis();
		// get HashMap of P(token|feedbackDocs)
		HashMap<String, Double> TokenRFScore = GetTokenRFScore(aQuery, TopK);
		long endTokenRFScoreTime = System.currentTimeMillis();
		System.out.println("token RF score time: " + (endTokenRFScoreTime - startTokenRFScoreTime) / 60000.0 + " min");

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
		long endDocScoreTime = System.currentTimeMillis();
		System.out.println("single doc score time: " + (endDocScoreTime - endTokenRFScoreTime) / 60000.0 + " min");

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
		long endDocListTime = System.currentTimeMillis();
		System.out.println("total doc score time: " + (endDocListTime - endDocScoreTime) / 60000.0 + " min");

		// (f) sort documentList in descending order
		documentList.sort((d1, d2) -> Double.compare(d2.score(), d1.score()));

		// return results
		List<Document> results = new ArrayList<>();
		for (int i = 0; i < TopN; i++) {
			results.add(documentList.get(i));
		}
		return results;
	}

	// too slow -> called ixreader too many times for posting
	public static List<Document> RetrieveQueryHW3( Query aQuery, int TopN, int TopK, double alpha ) throws Exception {
		long startTokenRFScoreTime = System.currentTimeMillis();
		// get HashMap of P(token|feedbackDocs)
		HashMap<String, Double> TokenRFScore = GetTokenRFScore(aQuery, TopK);
		long endTokenRFScoreTime = System.currentTimeMillis();
		System.out.println("token RF score time: " + (endTokenRFScoreTime - startTokenRFScoreTime) / 60000.0 + " min");

		// store docid in docIdSet (HashSet): removed duplicates
		Set<Integer> docIdSet = new HashSet<>();
		String[] queryTokens = aQuery.GetQueryContent().split(" ");
		for (String qt : queryTokens) {
			if (ixreader.CollectionFreq(qt) != 0) {
				int[][] posting = ixreader.getPostingList(qt);
				for (int[] p : posting) docIdSet.add(p[0]);
			}
		}
		long endDocIdTime = System.currentTimeMillis();
		System.out.println("docId time: " + (endDocIdTime - endTokenRFScoreTime) / 60000.0 + " min");

		// calculate new doc score
		// store it in document (Document) then in documentList (ArrayList)
		List<Document> documentList = new ArrayList<>();
		for (int docid : docIdSet) {
			String docno = ixreader.getDocno(docid);

			// calculate single doc score by given token, using Dirichlet prior smoothing:
			// P(token|D) = ( C(token, D) + mu*P(token|ref) ) / ( |D| + mu )
			double score = 1.0;
			for (String qt : queryTokens) {
				long collectionFreq = ixreader.CollectionFreq(qt); // for calculating P(token|ref)

				// note that token might not appear in the collection
				if (collectionFreq == 0) {
					continue;
				} else {
					long collectionTokenFreq = collectionFreq/collectionLength; // P(token|ref)

					int docLength = ixreader.docLength(docid); // |D|
					int docTokenFreq = ixreader.TokenFreq(qt, docid); // C(token, D)

					double pScore = (docTokenFreq + mu*collectionTokenFreq)/(docLength + mu);
					score *= (alpha*pScore + (1-alpha)*TokenRFScore.get(qt));
				}
			}

			Document document = new Document(Integer.toString(docid), docno, score);
			documentList.add(document);
		}
		long endDocScoreTime = System.currentTimeMillis();
		System.out.println("doc score time: " + (endDocScoreTime - endDocIdTime) / 60000.0 + " min");

		// sort documentList by document score
		documentList.sort((d1, d2) -> Double.compare(d2.score(), d1.score()));
		long endDocSortTime = System.currentTimeMillis();
		System.out.println("doc sort time: " + (endDocSortTime - endDocScoreTime) / 60000.0 + " min");

		// return results
		List<Document> results = new ArrayList<>();
		for (int i = 0; i < TopN; i++) {
			results.add(documentList.get(i));
		}
		return results;
	}

	public static HashMap<String,Double> GetTokenRFScore( Query aQuery, int TopK ) throws Exception {
		HashMap<String, Double> TokenRFScore = new HashMap<String, Double>();

		// retrieve TopK relevant documents
		List<Document> relevantDocList = queryRetrievalModel.retrieveQuery(aQuery, TopK);

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
			long collectionFreq = ixreader.CollectionFreq(qt); // for calculating P(token|ref)

			// note that token might not appear in the collection
			if (collectionFreq == 0) {
				TokenRFScore.put(qt, 1.0);
			} else {
				long collectionTokenFreq = collectionFreq/collectionLength; // P(token|ref)

				int feedbackDocLength = 0; // |feedbackDocs|
				int feedbackDocTokenFreq = 0; // C(token, feedbackDocs)

				// treat all feedbackDocs as one big pseudo document
				for (int i = 0; i < relevantDocid.size(); i++) {
					int docid = relevantDocid.get(i);
					feedbackDocLength += ixreader.docLength(docid);
					feedbackDocTokenFreq += ixreader.TokenFreq(qt, docid);
					// System.out.println("feedbackDocLength: " + feedbackDocLength + "\t feedbackDocTokenFreq: " + feedbackDocTokenFreq);
				}

				TokenRFScore.put(qt, (feedbackDocTokenFreq + mu*collectionTokenFreq)/(feedbackDocLength + mu));
			}
		}

		return TokenRFScore;
	}
	 */

}
