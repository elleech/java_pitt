/**
 * This is for testing the class of WordNormalizer & WordTokenizer
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.*;
import PreProcessData.*;

public class test_wordizer {

	public static void main(String[] args) {
		// TEST A: WordNormalizer
		WordNormalizer wordNormalizer = new WordNormalizer();
		char[] testWord = "UnIvERsitY".toCharArray();
		testWord = wordNormalizer.lowercase(testWord);
		System.out.println(testWord);
		System.out.println(wordNormalizer.stem(testWord));

		/* [TRYOUT] Stemmer
		Stemmer stemmer = new Stemmer();
		char[] testWord = "distention".toCharArray();
		stemmer.add(testWord, testWord.length);
		stemmer.stem();
		System.out.println(stemmer.toString());
		 */


		// TEST B: WordTokenizer
		char[] word = null;
		char[] testString = "copras-public@w3.org from January to March 2004: by subject   W3C home &gt;   Mailing    lists &gt;   Public &gt;".toCharArray();
		WordTokenizer wordTokenizer = new WordTokenizer(testString);
		while ((word = wordTokenizer.nextWord()) != null) {
			System.out.println(word);
		}

		/* [TRYOUT] StringTokenizer
		char[] testString1 = "copras-public@w3.org from January to March 2004: by subject   W3C home &gt;   Mailing    lists &gt;   Public &gt;".toCharArray();
		String delimiters = " ,./<>?;':\"[]\\{}|=-+_()*&^%$#@!~`\r\n\t";
		StringTokenizer stringTokenizer1 = new StringTokenizer(String.valueOf(testString1), delimiters);
		while (stringTokenizer1.hasMoreTokens()) {
			System.out.println(stringTokenizer1.nextToken().toCharArray());
		}
		char[] testString2 = "I'm about to cry...".toCharArray();
		StringTokenizer stringTokenizer2 = new StringTokenizer(String.valueOf(testString2), delimiters);
		while (stringTokenizer2.hasMoreTokens()) {
			System.out.println(stringTokenizer2.nextToken().toCharArray());
		}
		 */
	}

}
