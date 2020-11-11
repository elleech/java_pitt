package Search;

import java.io.*;
import java.util.*;

import Classes.Query;
import Classes.Document;
import IndexingLucene.MyIndexReader;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class QueryRetrievalModel {

	/*
	 * [STRUCTURES & THOUGHTS]
	 * <essential data>
	 * "indexReader" --> a MyIndexReader
	 * "mu"          --> a double of Dirichlet prior, default 2000.0
	 * 
	 * <class methods>
	 * Constructors  --> 1) initialize MyIndexReader
	 *                   2) initialize MyIndexReader and mu (optional)
	 * retrieveQuery --> extract query tokens, store needed docid in docIdSet (HashSet) based on query tokens
	 *                   calculate doc score based on given query, create and store document (Document) in documentList (ArrayList)
	 *                   sort documentList (ArrayList) by each document score
	 *                   return the top N results
	 * Dirichlet     --> calculate single doc score by given token, using Dirichlet prior smoothing
	 * docScore      --> calculate total doc score by given query
	 */
	protected MyIndexReader indexReader;
	private double mu = 2000.0; // in most cases, the optimal prior is around 2000

	public QueryRetrievalModel(MyIndexReader ixreader) {
		this.indexReader = ixreader;
	}

	// if user wants to modify mu
	public QueryRetrievalModel(MyIndexReader ixreader, double mu) {
		this.indexReader = ixreader;
		this.mu = mu;
	}

	/**
	 * Search for the topic information. 
	 * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
	 * TopN specifies the maximum number of results to be returned.
	 * 
	 * @param aQuery The query to be searched for.
	 * @param TopN The maximum number of returned document
	 * @return
	 */
	public List<Document> retrieveQuery( Query aQuery, int TopN ) throws IOException {
		// NT: you will find our IndexingLucene.Myindexreader provides method: docLength()
		// implement your retrieval model here, and for each input query, return the topN retrieved documents
		// sort the docs based on their relevance score, from high to low

		// store docid in docIdSet (HashSet): removed duplicates
		Set<Integer> docIdSet = new HashSet<>();
		String[] queryTokens = aQuery.GetTitle().split(" ");
		for (String qt : queryTokens) {
			if (this.indexReader.CollectionFreq(qt) != 0) {
				int[][] posting = this.indexReader.getPostingList(qt);
				for (int[] p : posting) docIdSet.add(p[0]);
			}
		}

		// calculate doc score and store it in document (Document) then in documentList (ArrayList)
		List<Document> documentList = new ArrayList<>();
		for (int docid : docIdSet) {
			String docno = this.indexReader.getDocno(docid);
			double score = docScore(aQuery, docid);
			Document document = new Document(Integer.toString(docid), docno, score);
			documentList.add(document);
		}

		// sort documentList by document score
		documentList.sort((d1, d2) -> Double.compare(d2.score(), d1.score()));

		// return results
		List<Document> results = new ArrayList<>();
		for (int i = 0; i < TopN; i++) {
			results.add(documentList.get(i));
		}
		return results;
	}

	// calculate single doc score by given token, using Dirichlet prior smoothing:
	// P(w|D) = ( C(w, D) + mu*P(w|ref) ) / ( |D| + mu )
	private double Dirichlet(String token, int docid) throws IOException {
		int docLength = this.indexReader.docLength(docid); // |D|
		int docTokenFreq = this.indexReader.TokenFreq(token, docid); // C(w, D)
		long collectionFreq = this.indexReader.CollectionFreq(token); // for calculating P(w|ref)
		long collectionLength = this.indexReader.collectionLength(); // for calculating P(w|ref)

		// note that topic may contain word that dosen't appear in the collection
		// need to detect and process such cases, for example, "Dysphagia"
		return (collectionFreq == 0) ? 
				1.0 : (docTokenFreq + this.mu*collectionFreq/collectionLength)/(docLength + this.mu);
	}

	// calculate total doc score by given query
	private double docScore(Query aQuery, int docid) throws IOException {
		double score = 1.0;
		String[] queryTokens = aQuery.GetTitle().split(" ");
		for (String qt : queryTokens) {
			score *= Dirichlet(qt, docid);
		}
		return score;
	}

}