package PreProcessData;

import Classes.Stemmer;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */
public class WordNormalizer {
	// you can add essential private methods or variables
	private Stemmer stemmer;

	// YOU MUST IMPLEMENT THIS METHOD
	public char[] lowercase( char[] chars ) {
		//transform the uppercase characters in the word to lowercase
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i]) && Character.isUpperCase(chars[i])) {
				chars[i] = Character.toLowerCase(chars[i]);
			}
		}
		return chars;
	}

	public String stem(char[] chars) {
		// use the stemmer in Classes package to do the stemming on input word, and return the stemmed word
		this.stemmer = new Stemmer();
		this.stemmer.add(chars, chars.length);
		this.stemmer.stem();
		String str = this.stemmer.toString();
		return str;
	}

}
