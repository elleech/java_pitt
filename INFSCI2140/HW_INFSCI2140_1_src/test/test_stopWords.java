/**
 * This is for testing the class of StopWordRemover
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.*;
import PreProcessData.*;

public class test_stopWords {

	public static void main(String[] args) throws IOException {
		StopWordRemover stopWordRemover = new StopWordRemover();
		char[] testWord1 = "the".toCharArray();
		char[] testWord2 = "ellee".toCharArray();
		System.out.println(stopWordRemover.isStopword(testWord1));
		System.out.println(stopWordRemover.isStopword(testWord2));

		/* [TRYOUT] StopWordRemover
		int lineNo = 0;

		BufferedReader br = new BufferedReader(new FileReader(".//" + Path.StopwordDir));

		String line = "";
		
		Set<String> stopWords = new HashSet<>();

		while ((line = br.readLine()) != null) {
			stopWords.add(line);
			lineNo++;
		}

		System.out.println(stopWords);
		System.out.println(stopWords.size());
		System.out.println("--> " + lineNo);
		// size different must be duplicates

		char[] testWord1 = {'a'};
		char[] testWord2 = {'z', 'e', 'r', 'o'};
		System.out.println(stopWords.contains(String.valueOf(testWord1)));
		System.out.println(stopWords.contains(String.valueOf(testWord2)));

		br.close();
		 */
	}

}
