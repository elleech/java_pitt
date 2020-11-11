/**
 * This is for testing the class of TrecwebCollection
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.*;
import PreProcessData.*;

public class test_trecWeb {

	public static void main(String[] args) throws IOException {
		DocumentCollection trecWeb = new TrecwebCollection();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> trecWebDoc = trecWeb.nextDocument();
			System.out.println(trecWebDoc);
		}

		/* [TRYOUT] TrecwebCollection
		int lineNo = 0;

		BufferedReader br = new BufferedReader(new FileReader(".//" + Path.DataWebDir));

		String line = "";

		Map<String, Object> doc = new HashMap<>();
		StringBuilder text = new StringBuilder();
		String docNo = "";

		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			if (line.equals("<DOC>")) {
				doc.clear();
				text.setLength(0);
				docNo = "N/A";
			}

			if (line.startsWith("<DOCNO>")) {
				docNo = line.substring(7, line.length()-8);
			}

			if (line.equals("</DOCHDR>")) {
				while (!(line = br.readLine()).equals("</DOC>")) {
					text.append(line);
				}
				System.out.println("--> " + docNo);
				System.out.println("== " + text);
				doc.put(docNo, removeHTMLTags(text.toString()));
				for (Map.Entry<String, Object> entry : doc.entrySet()) {
					System.out.println("doc: [" + entry.getKey() + "] " + entry.getValue().toString());
				}
				System.out.println();
			}

			lineNo++;
			if (lineNo > 200) break;
		}

		br.close();
		 */
	}

	private static String removeHTMLTags(String str) {
		if (str.length() > 0) {
			str = str.replaceAll("\\<.*?\\>", "");
		}
		return str;
	}

}
