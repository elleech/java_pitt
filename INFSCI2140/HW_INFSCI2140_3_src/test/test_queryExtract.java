/**
 * This is for testing the class of ExtractQuery
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Classes.Path;
import Classes.Query;
import Classes.Stemmer;
import Search.ExtractQuery;

public class test_queryExtract {

	public static void main(String[] args) throws IOException {
		ExtractQuery queries = new ExtractQuery();
		while (queries.hasNext()) {
			Query aQuery = queries.next();
			System.out.println(aQuery.GetTopicId() + ": " + aQuery.GetQueryContent());
			System.out.println("=> " + aQuery.GetTitle());
		}
	}

	/* [TRYOUT] ExtractQuery
	private static Set<String> stopWords;
	private static List<Query> queries;

	public static void main(String[] args) throws IOException {

		stopWords = new HashSet<>();
		queries = new ArrayList<>();

		try {
			String line = "";

			// read stopword.txt and store data in stopWords (HashSet)
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Path.StopwordDir));
			while ((line = bufferedReader.readLine()) != null) {
				stopWords.add(line);
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
				// 1) toLowerCase, 2) tokenize, 3) remove stopWords, 4) stem
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

			// print out and check
			for (Query q : queries) {
				System.out.println(q.GetTopicId());
				System.out.println(q.GetTitle());
				System.out.println(q.GetDesc());
				System.out.println(q.GetNarr());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isStopword(String word) {
		// method from HW1 StopWordRemover.java
		return stopWords.contains(word);
	}

	private static String stem(char[] chars) {
		// method from HW1 WordNormalizer.java
		Stemmer stemmer = new Stemmer();
		stemmer.add(chars, chars.length);
		stemmer.stem();
		String str = stemmer.toString();
		return str;
	}

	// 1) toLowerCase, 2) tokenize, 3) remove stopWords, 4) stem
	private static String preProcess(String line) {
		// combined methods from HW1 WordTokenizer.java and HW1Main.java
		String str = "";
		String[] temp = line.toLowerCase().split("[\s,.;:!?-_'\"]+");
		for (String s : temp) {
			if (!isStopword(s)) {
				str += (stem(s.toCharArray()) + " ");
			}
		}
		return str;
	}
	 */

}
