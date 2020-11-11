package Indexing;

import java.io.*;
import java.util.*;

import Classes.Path;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class PreProcessedCorpusReader {

	private BufferedReader bufferedReader;

	public PreProcessedCorpusReader(String type) throws IOException {
		// This constructor opens the pre-processed corpus file, Path.ResultHM1 + type
		// You can use your own version, or download from http://crystal.exp.sis.pitt.edu:8080/iris/resource.jsp
		// Close the file when you do not use it any more

		try {
			this.bufferedReader = new BufferedReader(new FileReader(".//" + Path.ResultHM1 + type));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Map<String, Object> NextDocument() throws IOException {
		// read a line for docNo and a line for content, put into the map with <docNo, content>

		try {
			/*
			 * [THOUGHTS]
			 * initialize Map (doc) and String (docNo)
			 * go through each line
			 * set the first content to String (docNo)
			 * put String (docNo) and the second content to Map (doc)
			 */
			Map<String, Object> doc = new HashMap<>();
			String docNo = this.bufferedReader.readLine();
			doc.put(docNo, this.bufferedReader.readLine().toCharArray());

			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}

		this.bufferedReader.close();
		return null;
	}

}
