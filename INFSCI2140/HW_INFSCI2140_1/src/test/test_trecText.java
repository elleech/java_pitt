/**
 * This is for testing the class of TrectextCollection
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.*;
import PreProcessData.*;

public class test_trecText {

	public static void main(String[] args) throws IOException {
		DocumentCollection trecText = new TrectextCollection();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> trecTextDoc = trecText.nextDocument();
			System.out.println(trecTextDoc);
		}

		/* [TRYOUT] TrectextCollection
		int lineNo = 0;

		BufferedReader br = new BufferedReader(new FileReader(".//" + Path.DataTextDir));

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
				docNo = line.substring(8, line.length()-9);
			}

			if (line.equals("<TEXT>")) {
				while (!(line = br.readLine()).equals("</TEXT>")) {
					text.append(line);
				}
			}

			if (line.equals("</DOC>")) {
				System.out.println("--> " + docNo);
				System.out.println("== " + text);
				doc.put(docNo, text.toString());
				for (Map.Entry<String, Object> entry : doc.entrySet()) {
					System.out.println("doc: [" + entry.getKey() + "] " + entry.getValue().toString());
				}
				System.out.println();
			}

			lineNo++;
			if (lineNo > 50) break;
		}

		br.close();
		 */
	}

}
