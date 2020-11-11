package PreProcessData;

import java.util.*;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 *
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	// you can add essential private methods or variables
	private StringTokenizer stringTokenizer;

	/** do we need to exclude all apostrophe symbols? what is the exception? **/
	private String delimiters = " ,./<>?;':\"[]\\{}|=-+_()*&^%$#@!~`\r\n\t";

	// YOU MUST IMPLEMENT THIS METHOD
	public WordTokenizer( char[] texts ) {
		// this constructor will tokenize the input texts (usually it is a char array for a whole document)
		this.stringTokenizer = new StringTokenizer(String.valueOf(texts), this.delimiters);
	}

	// YOU MUST IMPLEMENT THIS METHOD
	public char[] nextWord() {
		// read and return the next word of the document
		// or return null if it is the end of the document
		while (this.stringTokenizer.hasMoreTokens()) {
			return this.stringTokenizer.nextToken().toCharArray();
		}
		return null;
	}

}
