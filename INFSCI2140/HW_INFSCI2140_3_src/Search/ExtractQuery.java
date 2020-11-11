package Search;

import java.io.*;
import java.util.*;

import Classes.Path;
import Classes.Query;
import Classes.Stemmer;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class ExtractQuery {

	/*
	 * [STRUCTURES & THOUGHTS]
	 * <essential data>
	 * "stopWords" --> a HashSet of words from stopword.txt
	 * "queries"   --> an ArrayList of Query extracted from topics.txt
	 * "index"     --> an int indicates current position in queries (ArrayList)
	 * 
	 * <class methods>
	 * Constructor --> 1) read stopword.txt and store data in stopWords (HashSet)
	 *                 2) read topics.txt and store data in queries (ArrayList)
	 * hasNext     --> check if index (int) is at the end of queries (ArrayList)
	 * next        --> return the next query (Query) in queries (ArrayList)
	 * isStopword  --> check if input word (String) is a stop word
	 * stem        --> perform stemming to input chars (char[])
	 * preProcess  --> perform 1) toLowerCase, 2) tokenize, 3) remove stopWords, 4) stem to input line (String)
	 */
	private Set<String> stopWords;
	private List<Query> queries;
	private int index;

	public ExtractQuery() throws IOException {
		// you should extract the 4 queries from the Path.TopicDir
		// NT: the query content of each topic should be 1) tokenized, 2) to lowercase, 3) remove stop words, 4) stemming
		// NT: you can simply pick up title only for query, or you can also use title + description + narrative for the query content.

		this.stopWords = new HashSet<>();
		this.queries = new ArrayList<>();
		this.index = 0;

		try {
			String line = "";

			// read stopword.txt and store data in stopWords (HashSet)
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Path.StopwordDir));
			while ((line = bufferedReader.readLine()) != null) {
				this.stopWords.add(line);
			}
			bufferedReader.close();

			// read topics.txt and store data in queries (ArrayList)
			Query query = new Query();
			String queryContent = "";
			String topicId = "";
			bufferedReader = new BufferedReader(new FileReader(Path.TopicDir));
			while ((line = bufferedReader.readLine()) != null) {
				// initialize when a new query starts
				if (line.startsWith("<top>")) {
					query = new Query();
					queryContent = "";
					topicId = "";
				}

				// extract query number as topicId (String)
				if (line.startsWith("<num>")) {
					topicId = line.replaceAll("<num> Number: ", "");
				}

				// extract query content as queryContent (String)
				if (line.startsWith("<title>")) {
					queryContent += preProcess(line);
					while (!(line = bufferedReader.readLine()).equals("</top>")) {
						queryContent += preProcess(line);
					}
				}

				// store data to query (Query), then add query (Query) to queries (ArrayList)
				if (line.startsWith("</top>")) {
					query.SetQueryContent(queryContent);
					query.SetTopicId(topicId);
					queries.add(query);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean hasNext() {
		return this.index < this.queries.size();
	}
	public Query next() {
		return this.queries.get(this.index++);
	}

	private boolean isStopword(String word) {
		// method from HW1 StopWordRemover.java
		return this.stopWords.contains(word);
	}

	private String stem(char[] chars) {
		// method from HW1 WordNormalizer.java
		Stemmer stemmer = new Stemmer();
		stemmer.add(chars, chars.length);
		stemmer.stem();
		String str = stemmer.toString();
		return str;
	}

	// 1) toLowerCase, 2) tokenize, 3) remove stopWords, 4) stem
	private String preProcess(String line) {
		// combined methods from HW1 WordTokenizer.java and HW1Main.java
		String str = "";
		String[] temp = line.toLowerCase().split("[\s,.;:!?-_'\"]+");
		for (String s : temp) {
			if (!isStopword(s)) str += (stem(s.toCharArray()) + " ");
		}
		return str;
	}
}
