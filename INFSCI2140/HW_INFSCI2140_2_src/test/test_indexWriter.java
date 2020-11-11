/**
 * This is for testing the class of MyIndexWriter
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.Path;
import Indexing.*;

public class test_indexWriter {

	private static String type = "trectext";
	// private static String type = "trecweb";

	public static void main(String[] args) throws IOException {
		/*
		// test Path.IndexTextDir
		String path = Path.IndexTextDir;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
		bw.write("testing");
		bw.close();
		 */

		PreProcessedCorpusReader corpus = new PreProcessedCorpusReader(type);
		MyIndexWriter output = new MyIndexWriter(type);
		Map<String, Object> doc = null;

		int count = 0;
		long startTime = System.currentTimeMillis();
		while ((doc = corpus.NextDocument()) != null) {
			String docno = doc.keySet().iterator().next();
			char[] content = (char[]) doc.get(docno);
			output.IndexADocument(docno, content);

			count++;
			if (count%30000 == 0) {
				System.out.println("finish " + count + " docs");
			}
		}
		System.out.println("totaly document count: " + count);

		long endTime = System.currentTimeMillis();
		System.out.println("Writing time: " + (endTime-startTime)/1000.0 + " s");
		output.Close();
		long closeTime = System.currentTimeMillis();
		System.out.println("End of closing: " + (closeTime-endTime)/1000.0 + " s");
	}

	/* [TRYOUT] MyIndexWriter
	private static int docId;
	private static int blockId;

	private static String docFilePath;
	private static String blockFilePath;
	private static String indexFilePath;

	private static BufferedWriter docBufferedWriter;
	private static BufferedWriter blockBufferedWriter;

	private static Map<String, Map<Integer, Long>> dictionary; // map of term and posting(docId, termFreq)
	private static Map<String, Queue<Integer>> blockLocation; // map of term and blockId
	private static Map<String, long[]> termSum; // map of term and list of collectionFreq and docFreq

	public static void main(String[] args) throws IOException {
		docId = 0;
		blockId = 0;

		// don't use TreeMap if the data is large!
		dictionary = new HashMap<>();
		blockLocation = new HashMap<>();
		termSum = new HashMap<>();

		docFilePath = ".//src//test//test_docset." + type + ".txt";
		blockFilePath = ".//src//test//test_" + type + "_block";
		indexFilePath = ".//src//test//test_index" + type.substring(4) + ".txt";

		docBufferedWriter = new BufferedWriter(new FileWriter(new File(docFilePath), true));

		// read from PreProcessedCorpusReader
		PreProcessedCorpusReader ppcr = new PreProcessedCorpusReader(type);
		for (int i = 0; i < 5; i++) {
			Map<String, Object> corpusDoc = ppcr.NextDocument();

			String docno = corpusDoc.keySet().iterator().next();
			char[] content = (char[]) corpusDoc.get(docno);
			// char[] content = "normal activ normal anti normal normal ".toCharArray();

			// System.out.println(docno);
			// System.out.println(content);

			indexDoc(docno, content);
		}
		close();

		// System.out.println(wordCount(dictionary)); // for checking the consistency
		// System.out.println(dictionary);
	}

	// build dictionary and create posting
	public static void indexDoc(String docno, char[] content) throws IOException {
		docId++;

		int wordCount = 0; // for checking the consistency

		StringBuilder term = new StringBuilder(); // because input is char[]
		for (char c : content) {
			// form the term (remove white space)
			if (c != ' ') {
				term.append(c);
			}

			// finish forming the term, start putting info to the dictionary and posting
			else {
				wordCount++;
				Map<Integer, Long> posting = new HashMap<>(); // map of docId and termFreq

				// case 1: if the term does not exist in dictionary
				if (!dictionary.containsKey(term.toString())) {
					posting.put(docId, 1L);
					dictionary.put(term.toString(), posting);
				}

				// case 2: if the term exists in the dictionary
				else {
					posting = dictionary.get(term.toString());

					// case 2a: if the docId does not exist in the posting
					if (!posting.containsKey(docId)) posting.put(docId, 1L);
					// case 2b: if the docId exists in the posting
					else posting.put(docId, posting.get(docId)+1);

					dictionary.put(term.toString(), posting);
				}
				term.setLength(0);
			}
		}

		// docBufferedWriter.write(docId + "=" + docno + "(" + wordCount + ")\n");
		docBufferedWriter.write(docId + " " + docno + "\n");

		// write dictionary to a block file every 2 documents
		if (docId%2 == 0) blockWriter();
	}

	// write the remaining dictionary in the last block file, delete all block files, and close everything
	public static void close() throws IOException {
		// write the remaining dictionary to a block file
		blockWriter();
		// write blockLocation and termSum to index file
		indexWriter();
		docBufferedWriter.close(); // must close so the data can be actually written in the file
		blockBufferedWriter.close(); // must close so the data can be actually written in the file
	}

	// write dictionary to block file, keep records of blockLocation and termSum, and release dictionary memory
	private static void blockWriter() throws IOException {
		blockId++;

		try {
			blockBufferedWriter = new BufferedWriter(new FileWriter(new File(blockFilePath + blockId + ".txt")));

			for (Map.Entry<String, Map<Integer, Long>> d : dictionary.entrySet()) {
				String term = d.getKey();
				Map<Integer, Long> posting = d.getValue();

				long collectionFreq = 0; // sum of the termFreq in the dictionary
				int docFreq = posting.size(); // sum of the number of documents in the posting

				// action 1: write dictionary to the block file
				blockBufferedWriter.write(term + " ");
				for (Map.Entry<Integer, Long> p : posting.entrySet()) {
					int docId = p.getKey();
					long termFreq = p.getValue();

					blockBufferedWriter.write(docId + ":" + termFreq + ",");
					collectionFreq += termFreq;
				}
				blockBufferedWriter.write("\n");

				// action 2: keep records of blockLocation
				if (!blockLocation.containsKey(term)) {
					Queue<Integer> location = new LinkedList<>();
					location.add(blockId);
					blockLocation.put(term, location);
				} else {
					Queue<Integer> location = blockLocation.get(term);
					location.add(blockId);
					blockLocation.put(term, location);
				}

				// action 3: keep records of termSum
				long[] sum = new long[2];
				if (!termSum.containsKey(term)) {
					sum[0] = collectionFreq;
					sum[1] = docFreq;
					termSum.put(term, sum);
				} else {
					sum[0] = termSum.get(term)[0] + collectionFreq;
					sum[1] = termSum.get(term)[1] + docFreq;
					termSum.put(term, sum);
				}
			}
			blockBufferedWriter.close(); // must close so the data can be actually written in the file

			// action 4: release memory of dictionary
			dictionary.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write blockLocation and termSum to index file
	private static void indexWriter() throws IOException {
		try {
			blockBufferedWriter = new BufferedWriter(new FileWriter(new File(indexFilePath)));

			for (Map.Entry<String, Queue<Integer>> bl : blockLocation.entrySet()) {
				String term = bl.getKey();
				Queue<Integer> location = bl.getValue();
				long collectionFreq = termSum.get(term)[0];
				int docFreq = (int) termSum.get(term)[1];

				blockBufferedWriter.write(term + " " + collectionFreq + " " + docFreq + " ");

				while (!location.isEmpty()) {
					int blockId = location.poll();
					blockBufferedWriter.write(blockId + ",");
				}
				blockBufferedWriter.write("\n");
			}
			blockBufferedWriter.close(); // must close so the data can be actually written in the file
			blockLocation.clear();
			termSum.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// for checking the consistency
	private static Map<Integer, Integer> wordCount(Map<String, Map<Integer, Integer>> dictionary) { 
		Map<Integer, Integer> count = new HashMap<>(); // map of docId and termCount
		for (Map.Entry<String, Map<Integer, Integer>> d : dictionary.entrySet()) {
			Map<Integer, Integer> posting = d.getValue(); // term posting (map of docId and termFreq)
			for (Map.Entry<Integer, Integer> p : posting.entrySet()) {
				int docId = p.getKey();
				int termFreq = p.getValue();
				if (!count.containsKey(docId)) count.put(docId, termFreq);
				else count.put(docId, count.get(docId)+termFreq);
			}
		}
		return count;
	}
	 */

}
