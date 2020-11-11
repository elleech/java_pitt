/**
 * This is for testing the class of PreProcessedCorpusReader
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.Path;
import Indexing.PreProcessedCorpusReader;

public class test_corpusReader {

	private static String type = "trectext";
	// private static String type = "trecweb";

	public static void main(String[] args) throws IOException {
		// test the class
		PreProcessedCorpusReader corpus = new PreProcessedCorpusReader(type);
		Map<String, Object> doc = null;
		int count = 0;
		while ((doc = corpus.NextDocument()) != null) {
			// System.out.println(doc);

			/*
			// print out the content
			for (Map.Entry<String, Object> entry : doc.entrySet()) {
				for (char c : (char[]) entry.getValue()) {
					System.out.print(c);
				}
			}
			 */

			// System.out.println();
			count++;
			if (count%30000 == 0) System.out.println("finish " + count + " docs");
		}
		System.out.println("totaly document count:  " + count);

		/* [TRYOUT] PreProcessedCorpusReader
		BufferedReader br = new BufferedReader(new FileReader(".//" + Path.ResultHM1 + type));
		// printLine(br, 50);

		Map<String, Object> doc = new HashMap<>();
		String docNo = br.readLine();
		doc.put(docNo, br.readLine());

		for (Map.Entry<String, Object> entry : doc.entrySet()) {
			System.out.println("doc: [" + entry.getKey() + "] " + entry.getValue().toString());
		}

		br.close();
		 */
	}

	// print out first #lines of the doc
	private static void printLine(BufferedReader br, int lines) {
		int lineNo = 0;
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				lineNo++;
				if (lineNo > lines) break;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
