package Indexing;

import java.io.*;
import java.util.*;

import Classes.Path;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

/*
 * [STRUCTURES & THOUGHTS]
 * <essential data>
 * "blockLocation" --> term and blockId
 * "termSum"       --> term and collectionFreq (#times the term in the collection => the total sum of termFreq),
 *                              docFreq (#document has the term => the total sum of posting length of the term)
 * 
 * <class methods>
 * Constructor       --> initialize file paths, blockLocation and termSum (using getRecord() method)
 * GetDocid          --> read docset file, search for the given docno (String), and return docId (int)
 * GetDocno          --> read docset file, search for the given docId (int), and return docno (String)
 * GetPostingList    --> search for the given term (String) in blockLocation, get the list of blockId
 *                       search for the given term (String) in block file based on blockId, get a map of posting
 *                       convert the map of posting to 2D array of postingList and return it
 * GetDocFreq        --> search for the given term (String) in termSum and return docFreq (int)
 * GetCollectionFreq --> search for the given term (String) in termSum and return collectionFreq (long)
 * Close             --> close and clear everything
 * getRecord         --> read index_ file and store data to blockLocation and termSum
 * 
 * <note>
 * term means token
 */
public class MyIndexReader {
	// you are suggested to write very efficient code here, otherwise, your memory cannot hold our corpus...

	private String docFilePath;
	private String blockFilePath;
	private String indexFilePath;

	private BufferedReader bufferedReader;
	private BufferedReader blockBufferedReader;

	private Map<String, ArrayList<Integer>> blockLocation; // map of term and blockId
	private Map<String, long[]> termSum; // map of term and list of collectionFreq and docFreq

	public MyIndexReader( String type ) throws IOException {
		// read the index files you generated in task 1
		// remember to close them when you finish using them
		// use appropriate structure to store your index

		this.blockLocation = new HashMap<>();
		this.termSum = new HashMap<>();

		// determine directory based on type
		switch (type) {
		case "trectext":
			this.docFilePath = Path.DataTextDir;
			this.blockFilePath = Path.IndexTextDir + "block";
			this.indexFilePath = Path.IndexTextDir + "index.txt";
			break;
		case "trecweb":
			this.docFilePath = Path.DataWebDir;
			this.blockFilePath = Path.IndexWebDir + "block";
			this.indexFilePath = Path.IndexWebDir + "index.txt";
			break;
		default:
			throw new IOException("Invalid type");
		}

		// initialize blockLocation and termSum
		getRecord();
	}

	// get the non-negative integer docId for the requested docno
	// if the requested docno does not exist in the index, return -1
	public int GetDocid( String docno ) throws IOException {
		try {
			String line = "";
			this.bufferedReader = new BufferedReader(new FileReader(this.docFilePath));
			while ((line = this.bufferedReader.readLine()) != null) {
				String[] temp = line.split(" "); // --> docId docno
				if (temp[1].equals(docno)) {
					return Integer.parseInt(temp[0]);
				}
			}
			this.bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// retrieve the docno for the integer docid
	public String GetDocno( int docid ) throws IOException {
		try {
			String docId = Integer.toString(docid);
			String line = "";
			this.bufferedReader = new BufferedReader(new FileReader(this.docFilePath));
			while ((line = this.bufferedReader.readLine()) != null) {
				String[] temp = line.split(" "); // --> docId docno
				if (temp[0].equals(docId)) {
					return temp[1];
				}
			}
			this.bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the posting list for the requested token.
	 * 
	 * The posting list records the documents' docids the token appears and corresponding frequencies of the term, such as:
	 *  
	 *  [docid]		[freq]
	 *  1			3
	 *  5			7
	 *  9			1
	 *  13			9
	 * 
	 * ...
	 * 
	 * In the returned 2-dimension array, the first dimension is for each document, and the second dimension records the docid and frequency.
	 * 
	 * For example:
	 * array[0][0] records the docid of the first document the token appears.
	 * array[0][1] records the frequency of the token in the documents with docid = array[0][0]
	 * ...
	 * 
	 * NOTE that the returned posting list array should be ranked by docid from the smallest to the largest. 
	 * 
	 * @param token
	 * @return
	 */
	public int[][] GetPostingList( String token ) throws IOException {
		/*
		 * [THOUGHTS]
		 * search term in blockLocation, if found, extract the list of blockId
		 * search term in block file, if found, append data to a map of posting
		 * convert the map of posting to 2D array of postingList
		 */
		// action 1: search term in blockLocation
		if (this.blockLocation.containsKey(token)) {
			int[][] postingList = null; // list of list of docId and termFreq
			Map<Integer, Integer> posting = new HashMap<>(); // map of docId and termFreq

			ArrayList<Integer> location = this.blockLocation.get(token); // list of blockId

			// action 2: search term in block file
			for (int i = 0; i < location.size(); i++) {
				int blockId = location.get(i);

				// read block file by blockId
				String line = "";
				this.blockBufferedReader = new BufferedReader(new FileReader(this.blockFilePath + blockId + ".txt"));
				while ((line = this.blockBufferedReader.readLine()) != null) {
					if (line.startsWith(token + " ")) {
						String[] dictionary = line.split(" "); // -> term posting1,posting2,...
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
			this.blockBufferedReader.close();

			// action 3: convert the map of posting to 2D array of postingList
			postingList = new int[posting.size()][2];
			int k = 0;
			for (Map.Entry<Integer, Integer> p : posting.entrySet()) {
				// docId = p.getKey(); termFreq = p.getValue();
				postingList[k][0] = p.getKey();
				postingList[k][1] = p.getValue();

				k++;
			}
			return postingList;
		} else {
			return null;
		}
	}

	// return the number of documents that contains the token.
	public int GetDocFreq( String token ) throws IOException {
		return this.termSum.containsKey(token) ? (int) this.termSum.get(token)[1] : 0;
	}

	// return the total number of times the token appears in the collection.
	public long GetCollectionFreq( String token ) throws IOException {
		return this.termSum.containsKey(token) ? this.termSum.get(token)[0] : 0;
	}

	public void Close() throws IOException {
		try {
			this.bufferedReader.close();
			this.blockBufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
		this.blockLocation.clear();
		this.termSum.clear();
	}

	// store data to blockLocation and termSum
	private void getRecord() throws IOException {
		try {
			String line = "";
			this.bufferedReader = new BufferedReader(new FileReader(this.indexFilePath));
			while ((line = this.bufferedReader.readLine()) != null) {
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
				this.blockLocation.put(term, location);

				// store data to termSum
				long[] sum = new long[2];
				sum[0] = collectionFreq;
				sum[1] = docFreq;
				this.termSum.put(term, sum);
			}
			this.bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}