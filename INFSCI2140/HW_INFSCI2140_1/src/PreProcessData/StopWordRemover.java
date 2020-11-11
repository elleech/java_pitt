package PreProcessData;

import java.io.*;
import java.util.*;

import Classes.*;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class StopWordRemover {
	//you can add essential private methods or variables.
	private BufferedReader bufferedReader;
	private Set<String> stopWords;

	public StopWordRemover( ) {
		// load and store the stop words from the fileinputstream with appropriate data structure
		// that you believe is suitable for matching stop words.
		// address of stopword.txt should be Path.StopwordDir

		this.stopWords = new HashSet<>();

		try {
			this.bufferedReader = new BufferedReader(new FileReader(".//" + Path.StopwordDir));
			
			String line = "";
			while ((line = this.bufferedReader.readLine()) != null) {
				this.stopWords.add(line);
			}
			
			this.bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// YOU MUST IMPLEMENT THIS METHOD
	public boolean isStopword( char[] word ) {
		// return true if the input word is a stopword, or false if not
		return this.stopWords.contains(String.valueOf(word));
	}
}
