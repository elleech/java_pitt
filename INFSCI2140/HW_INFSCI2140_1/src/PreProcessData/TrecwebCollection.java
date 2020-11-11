package PreProcessData;

import java.io.*;
import java.util.*;
import Classes.Path;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */
public class TrecwebCollection implements DocumentCollection {
	// you can add essential private methods or variables
	private BufferedReader bufferedReader;
	private String removeHTMLTags(String str) {
		if (str.length() > 0) {
			str = str.replaceAll("\\<.*?\\>", "");
		}
		return str;
	}

	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrecwebCollection() throws IOException {
		// This constructor should open the file existing in Path.DataWebDir
		// and also should make preparation for function nextDocument()
		// you cannot load the whole corpus into memory here!!

		try {
			this.bufferedReader = new BufferedReader(new FileReader(".//" + Path.DataWebDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NT: the returned content of the document should be cleaned, all html tags should be removed.
		// NTT: remember to close the file that you opened, when you do not use it any more

		try {
			/*
			 * [THOUGHTS]
			 * go through each line
			 * if bump into "<DOC>"     --> initialize Map (doc), StringBuilder (text) and String (docNo)
			 * if bump into "<DOCNO>"   --> set String (docNo) to the latest DOCNO
			 * if bump into "</DOCHDR>" --> go through lines until bump into "</DOC>",
			 *                              append context into StringBuilder (text),
			 *                              remove HTML tags inside StringBuilder (text),
			 *                              put data in Map (doc) and return it
			 */
			String line = "";

			Map<String, Object> doc = new HashMap<>();
			StringBuilder text = new StringBuilder();
			String docNo = "";

			while ((line = this.bufferedReader.readLine()) != null) {
				if (line.equals("<DOC>")) {
					doc.clear();
					text.setLength(0);
					docNo = "N/A";
				}

				if (line.startsWith("<DOCNO>")) {
					docNo = line.substring(7, line.length()-8);
				}

				if (line.equals("</DOCHDR>")) {
					while (!(line = this.bufferedReader.readLine()).equals("</DOC>")) {
						text.append(line);
					}
					doc.put(docNo, removeHTMLTags(text.toString()).toCharArray());
					return doc;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.bufferedReader.close();
		return null;
	}

}
