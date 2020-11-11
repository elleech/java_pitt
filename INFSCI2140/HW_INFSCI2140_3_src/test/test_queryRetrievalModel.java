/**
 * This is for testing the class of QueryRetrievalModel
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.Document;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import Search.ExtractQuery;
import Search.QueryRetrievalModel;

public class test_queryRetrievalModel {

	public static void main(String[] args) throws IOException {
		MyIndexReader ixreader = new MyIndexReader("trectext");
		QueryRetrievalModel model = new QueryRetrievalModel(ixreader);
		ExtractQuery queries = new ExtractQuery();

		int n = 0;
		if (n < 4) for (int i = 0; i < n; i++) queries.next();
		Query aQuery = queries.next();

		long startTime = System.currentTimeMillis();
		List<Document> results = model.retrieveQuery(aQuery, 20);
		if (results != null) {
			int rank = 1;
			for (Document result : results) {
				System.out.println(aQuery.GetTopicId() + " Q0 " + result.docno() + " " + rank + " " + result.score() + " MYRUN");
				rank++;
			}
		}
		long endTime = System.currentTimeMillis(); // end time of running code
		System.out.println("\n\n4 queries search time: " + (endTime - startTime) / 60000.0 + " min");
		ixreader.close();
	}

	/* [TRYOUT] QueryRetrievalModel
	private static MyIndexReader indexReader;
	private static ExtractQuery queries;

	private static double mu = 2000.0;

	public static void main(String[] args) throws IOException {
		indexReader = new MyIndexReader("trectext");
		queries = new ExtractQuery();

		// check Dirichlet() method
		String testToken = "yhoo";
		int[][] temp = indexReader.getPostingList(testToken);
		for (int[] t : temp) {
			int docid = t[0];
			int freq = t[1];
			System.out.print("docid: " + docid + "\t freq: " + freq);
			System.out.print("\t CHECK: " + indexReader.TokenFreq(testToken, docid));
			System.out.println("\t Dirichlet: " + Dirichlet(testToken, docid));
		}
		// check for token that doesn't appear in collection
		System.out.print("CHECK total freq: " + indexReader.CollectionFreq("dysphagia"));
		System.out.println("\t Dirichlet: " + Dirichlet("dysphagia", 0) + "\n");

		// modify n for #query, n should be no greater than 4 (n = 0, 1, 2, 3)
		int n = 0;
		if (n < 4) for (int i = 0; i < n; i++) queries.next();
		Query query = queries.next();
		System.out.println(query.GetTopicId() + ": " + query.GetTitle());

		// store docid to docIdSet (HashSet): removed duplicates
		Set<Integer> docIdSet = new HashSet<>();
		String[] queryTokens = query.GetTitle().split(" ");
		for (String qt : queryTokens) {
			int count = 0; // same as indexReader.DocFreq(qt)
			if (indexReader.CollectionFreq(qt) != 0) {
				int[][] posting = indexReader.getPostingList(qt);
				for (int[] p : posting) {
					int docid = p[0];
					docIdSet.add(docid);
					// System.out.print(docid + "; "); // weird cannot print, count it instead
					count++;
				}
				System.out.println(qt + ": " + count + "\t CHECK: " + indexReader.DocFreq(qt));
			}
		}
		System.out.print("total #docs (no duplicates): " + docIdSet.size());

		long startTimeScoring = System.currentTimeMillis();
		// calculate doc score and store it in document (Document) then in documentList (ArrayList)
		List<Document> documentList = new ArrayList<>();
		for (int docid : docIdSet) {
			String docno = indexReader.getDocno(docid);
			double score = docScore(query, docid);
			Document document = new Document(Integer.toString(docid), docno, score);
			documentList.add(document);
		}
		System.out.println("\t CHECK: " + documentList.size());
		long endTimeScoring = System.currentTimeMillis();
		System.out.println("=== scoring time: " + (endTimeScoring - startTimeScoring)/60000.0 + " min ===");

		long startTimeSorting = System.currentTimeMillis();
		// sort documentList by document score
		documentList.sort((d1, d2) -> Double.compare(d2.score(), d1.score()));
		long endTimeSorting = System.currentTimeMillis();
		System.out.println("=== sorting time: " + (endTimeSorting - startTimeSorting)/60000.0 + " min ===");

		// print results
		for (int i = 0; i < 20; i++) {
			Document result = documentList.get(i);
			System.out.println(result.docno() + "\t" + (i+1) + "\t" + result.score() + "\t MYRUN");
		}

		indexReader.close();
	}

	// calculate single doc score by given token, using Dirichlet prior smoothing:
	// P(w|D) = ( C(w, D) + mu*P(w|ref) ) / ( |D| + mu )
	private static double Dirichlet(String token, int docid) throws IOException {
		int docLength = indexReader.docLength(docid); // |D|
		int docTokenFreq = indexReader.TokenFreq(token, docid); // C(w, D)
		long collectionFreq = indexReader.CollectionFreq(token); // for calculating P(w|ref)
		long collectionLength = indexReader.collectionLength(); // for calculating P(w|ref)

		// note that topic may contain word that dosen't appear in the collection
		// need to detect and process such cases, for example, "Dysphagia"
		return (collectionFreq == 0) ? 
				1.0 : (docTokenFreq + mu*collectionFreq/collectionLength)/(docLength + mu);
	}

	// calculate total doc score by given query
	private static double docScore(Query query, int docid) throws IOException {
		double score = 1.0;
		String[] queryTokens = query.GetTitle().split(" ");
		for (String qt : queryTokens) {
			score *= Dirichlet(qt, docid);
		}
		return score;
	}
	 */

}
