/**
 * This is for testing the class of MyIndexReader
 * YEC24@pitt.edu
 */

package test;

import java.io.*;
import java.util.*;

import Indexing.*;

public class test_indexReader {

	private static String type = "trectext";
	// private static String type = "trecweb";

	public static void main(String[] args) throws IOException {
		String token = "yhoo"; // for trectext
		// String token = "acow"; // for trecweb

		long startTime = System.currentTimeMillis();
		MyIndexReader ixreader = new MyIndexReader(type);

		long getFreqTime = System.currentTimeMillis();
		int df = ixreader.GetDocFreq(token);
		long ctf = ixreader.GetCollectionFreq(token);
		System.out.println(" >> the token \"" + token + "\" appeared in " + df + " documents and " + ctf + " times in total");

		long endTime = System.currentTimeMillis();
		if (df > 0) {
			int[][] posting = ixreader.GetPostingList(token);
			for (int ix = 0; ix < posting.length; ix++) {
				int docid = posting[ix][0];
				int freq = posting[ix][1];
				String docno = ixreader.GetDocno(docid);
				System.out.printf("    %20s    %6d    %6d\n", docno, docid, freq);
			}
		}
		ixreader.Close();
		long closeTime = System.currentTimeMillis();

		System.out.println("Init time: " + (getFreqTime-startTime)/1000.0 + " s");
		System.out.println("2 GetFreq time: " + (endTime-getFreqTime)/1000.0 + " s");
		System.out.println("End of closing: " + (closeTime-endTime)/1000.0 + " s");
	}

	/* [TRYOUT] MyIndexWriter
	private static String docFilePath;
	private static String blockFilePath;
	private static String indexFilePath;

	private static BufferedReader bufferedReader;
	private static BufferedReader blockBufferedReader;

	private static Map<String, ArrayList<Integer>> blockLocation; // map of term and blockId
	private static Map<String, long[]> termSum; // map of term and list of collectionFreq and docFreq

	public static void main(String[] args) throws IOException {
		// don't use TreeMap if the data is large!
		blockLocation = new HashMap<>();
		termSum = new HashMap<>();

		docFilePath = ".//src//test//test_docset." + type + ".txt";
		blockFilePath = ".//src//test//test_" + type + "_block";
		indexFilePath = ".//src//test//test_index" + type.substring(4) + ".txt";

		// get data from index file, and build map of blockLocation and termSum
		getRecord();
		System.out.println(blockLocation);
		for (Map.Entry<String, long[]> ts : termSum.entrySet()) {
			System.out.println(ts.getKey() + " (collectionFreq:" + ts.getValue()[0] + " docFreq:" + ts.getValue()[1] + ")");
		}

		// test of getDocId method
		System.out.println(getDocId("XIE19960101.0033"));
		System.out.println(getDocId("XIE19960101.0004"));

		// test of getDocNo method
		System.out.println(getDocNo(33));
		System.out.println(getDocNo(5));

		// test of getCollectionFreq method
		System.out.println(getCollectionFreq("ellee"));
		System.out.println(getCollectionFreq("jordan"));

		// test of getDocFreq method
		System.out.println(getDocFreq("ellee"));
		System.out.println(getDocFreq("jordan"));

		// test of getPostingList method
		int[][] posting = getPostingList("jordan");
		if (posting != null) {
			for (int i = 0; i < posting.length; i++) {
				System.out.println("docId: " + posting[i][0] + " termFreq: " + posting[i][1]);
			}
		}

		close();
		System.out.println(blockLocation);
		System.out.println(termSum);
	}

	public static int getDocId(String docno) throws IOException {
		try {
			String line = "";
			bufferedReader = new BufferedReader(new FileReader(docFilePath));
			while ((line = bufferedReader.readLine()) != null) {
				String[] temp = line.split(" "); // --> docId docno
				if (temp[1].equals(docno)) {
					return Integer.parseInt(temp[0]);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String getDocNo(int docid) throws IOException {
		try {
			String docId = Integer.toString(docid);
			String line = "";
			bufferedReader = new BufferedReader(new FileReader(docFilePath));
			while ((line = bufferedReader.readLine()) != null) {
				String[] temp = line.split(" "); // --> docId docno
				if (temp[0].equals(docId)) {
					return temp[1];
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long getCollectionFreq(String token) throws IOException {
		return termSum.containsKey(token) ? termSum.get(token)[0] : 0;
	}

	public static int getDocFreq(String token) throws IOException {
		return termSum.containsKey(token) ? (int) termSum.get(token)[1] : 0;
	}

	public static int[][] getPostingList(String token) throws IOException {
		// action 1: search term in blockLocation
		if (blockLocation.containsKey(token)) {
			int[][] postingList = null; // list of list of docId and termFreq
			Map<Integer, Integer> posting = new HashMap<>(); // map of docId and termFreq

			ArrayList<Integer> location = blockLocation.get(token); // list of blockId

			// action 2: search term in block file
			for (int i = 0; i < location.size(); i++) {
				int blockId = location.get(i);

				// read block file by blockId
				String line = "";
				blockBufferedReader = new BufferedReader(new FileReader(blockFilePath + blockId + ".txt"));
				while ((line = blockBufferedReader.readLine()) != null) {
					if (line.startsWith(token + " ")) {
						String[] dictionary = line.split(" "); // --> term posting1,posting2,...
						String[] temp = dictionary[1].split(","); // list of docId:termFreq

						for (int j = 0; j < temp.length; j++) {
							int docId = Integer.parseInt(temp[j].split(":")[0]);
							int termFreq = Integer.parseInt(temp[j].split(":")[1]);

							posting.put(docId, termFreq);
						}

						break; // end of while loop, move on reading another block file
					}
				}
			}
			blockBufferedReader.close();

			// action 3: convert the map of posting to 2D array of postingList
			postingList = new int[posting.size()][2];
			int k = 0;
			for (Map.Entry<Integer, Integer> p : posting.entrySet()) {
				int docId = p.getKey();
				int termFreq = p.getValue();

				postingList[k][0] = docId;
				postingList[k][1] = termFreq;

				k++;
			}
			return postingList;
		} else {
			return null;
		}
	}

	public static void close() throws IOException {
		try {
			bufferedReader.close();
			blockBufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
		blockLocation.clear();
		termSum.clear();
	}

	// store data to blockLocation and termSum
	private static void getRecord() throws IOException {
		try {
			String line = "";
			bufferedReader = new BufferedReader(new FileReader(indexFilePath));
			while ((line = bufferedReader.readLine()) != null) {
				String temp[] = line.split(" "); // --> term collectionFreq docFreq blockId1,blockId2,...

				String term = temp[0];
				long collectionFreq = Long.parseLong(temp[1]);
				int docFreq = Integer.parseInt(temp[2]);
				String[] loc = temp[3].split(",");

				// store data to blockLocation
				ArrayList<Integer> location = new ArrayList<>();
				for (int i = 0; i < loc.length; i++) {
					location.add(Integer.parseInt(loc[i]));
				}
				blockLocation.put(term, location);

				// store data to termSum
				long[] sum = new long[2];
				sum[0] = collectionFreq;
				sum[1] = docFreq;
				termSum.put(term, sum);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 */

}
