package PreProcessData;

import java.io.*;
import java.util.*;
import Classes.Path;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */
public class TrectextCollection implements DocumentCollection {
	// you can add essential private methods or variables
	private BufferedReader bufferedReader;

	// YOU SHOULD IMPLEMENT THIS METHOD
	public TrectextCollection() throws IOException {
		// This constructor should open the file existing in Path.DataTextDir
		// and also should make preparation for function nextDocument()
		// you cannot load the whole corpus into memory here!!

		try {
			this.bufferedReader = new BufferedReader(new FileReader(".//" + Path.DataTextDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NTT: remember to close the file that you opened, when you do not use it any more

		try {
			/*
			 * [THOUGHTS]
			 * go through each line
			 * if bump into "<DOC>"   --> initialize Map (doc), StringBuilder (text) and String (docNo)
			 * if bump into "<DOCNO>" --> set String (docNo) to the latest DOCNO
			 * if bump into "<TEXT>"  --> go through lines until bump into "</TEXT>",
			 *                            append context into StringBuilder (text)
			 * if bump into "</DOC>"  --> put data in Map (doc) and return it
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
					docNo = line.substring(8, line.length()-9);
				}

				if (line.equals("<TEXT>")) {
					while (!(line = this.bufferedReader.readLine()).equals("</TEXT>")) {
						text.append(line);
					}
				}

				if (line.equals("</DOC>")) {
					doc.put(docNo, text.toString().toCharArray());
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
