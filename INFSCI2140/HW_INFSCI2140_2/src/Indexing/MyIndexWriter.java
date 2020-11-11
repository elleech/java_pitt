package Indexing;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import Classes.Path;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

/*
 * [STRUCTURES & THOUGHTS]
 * <essential data>
 * "dictionary" --> term and posting
 * "posting"    --> docId (int, different from docno, String) and termFreq (#times the term in the doc)
 * 
 * <additional data>
 * "blockLocation" --> term and blockId
 * "termSum"       --> term and collectionFreq (#times the term in the collection => the total sum of termFreq),
 *                              docFreq (#document has the term => the total sum of posting length of the term)
 * 
 * <generate files>
 * docset  --> docId and docno
 * index  --> blockLocation and termSum
 * block_ --> dictionary
 * 
 * <class methods>
 * Constructor    --> initialize file paths and others
 * IndexADocument --> build dictionary, write docId and the corresponding docno in docset file,
 *                    and write the dictionary to a block file every 20000 documents
 * Close          --> write the remaining dictionary to the last block file,
 *                    write the blockLocation and termSum to index file,
 *                    clear blockLocation and termSum, and close everything
 * blockWriter    --> write dictionary to block file, keep records of blockLocation and termSum, 
 *                    and release the memory of dictionary
 * indexWriter    --> write blockLocation and termSum to index file
 * 
 * <note>
 * I decided to store docId-docno pairs to "docset" file, and the blockLocation and termSum to "index" file
 * however, later according to TA, "docset" file should be the result from assignment 1
 */
public class MyIndexWriter {
	// I suggest you to write very efficient code here, otherwise, your memory cannot hold our corpus...

	private int docId;
	private int blockId;

	private String docFilePath;
	private String blockFilePath;
	private String indexFilePath;

	private BufferedWriter docBufferedWriter;
	private BufferedWriter blockBufferedWriter;

	private Map<String, Map<Integer, Long>> dictionary; // map of term and posting(docId, termFreq)
	private Map<String, Queue<Integer>> blockLocation; // map of term and blockId
	private Map<String, long[]> termSum; // map of term and list of collectionFreq and docFreq

	public MyIndexWriter(String type) throws IOException {
		// This constructor should initiate the FileWriter to output your index files
		// remember to close files if you finish writing the index

		this.docId = 0;
		this.blockId = 0;

		this.dictionary = new HashMap<>();
		this.blockLocation = new HashMap<>();
		this.termSum = new HashMap<>();

		// determine directory based on type
		switch (type) {
		case "trectext":
			this.docFilePath = Path.DataTextDir;
			Files.createDirectories(Paths.get(Path.IndexTextDir));
			this.blockFilePath = Path.IndexTextDir + "block";
			this.indexFilePath = Path.IndexTextDir + "index.txt";
			break;
		case "trecweb":
			this.docFilePath = Path.DataWebDir;
			Files.createDirectories(Paths.get(Path.IndexWebDir));
			this.blockFilePath = Path.IndexWebDir + "block";
			this.indexFilePath = Path.IndexWebDir + "index.txt";
			break;
		default:
			throw new IOException("Invalid type");
		}

		// initialize doc file writer
		try {
			this.docBufferedWriter = new BufferedWriter(new FileWriter(new File(this.docFilePath), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void IndexADocument(String docno, char[] content) throws IOException {
		// you are strongly suggested to build the index by installments
		// you need to assign the new non-negative integer docId to each document, which will be used in MyIndexReader

		this.docId++;

		// form term and build dictionary
		StringBuilder term = new StringBuilder(); // because input is char[]
		for (char c : content) {
			// form the term (remove white space)
			if (c != ' ') {
				term.append(c);
			}

			// finish forming the term, start putting info to the dictionary and posting
			else {
				Map<Integer, Long> posting = new HashMap<>(); // map of docId and termFreq

				// case 1: if the term does not exist in dictionary
				if (!this.dictionary.containsKey(term.toString())) {
					posting.put(docId, 1L);
					this.dictionary.put(term.toString(), posting);
				}

				// case 2: if the term exists in the dictionary
				else {
					posting = this.dictionary.get(term.toString());

					// case 2a: if the docId does not exist in the posting
					if (!posting.containsKey(docId)) posting.put(docId, 1L);
					// case 2b: if the docId exists in the posting
					else posting.put(docId, posting.get(docId)+1);

					this.dictionary.put(term.toString(), posting);
				}
				term.setLength(0);
			}
		}

		this.docBufferedWriter.write(docId + " " + docno + "\n");

		// write dictionary to a block file every 20000 documents
		if (docId%20000 == 0) blockWriter();
	}

	public void Close() throws IOException {
		// close the index writer, and you should output all the buffered content (if any).
		// if you write your index into several files, you need to fuse them here.

		// write the remaining dictionary to a block file
		blockWriter();

		// write blockLocation and termSum to index file
		indexWriter();

		this.docBufferedWriter.close(); // must close so the data can be actually written in the file
		this.blockBufferedWriter.close(); // must close so the data can be actually written in the file
	}

	// write dictionary to block file, keep records of blockLocation and termSum, and release the memory of dictionary
	private void blockWriter() throws IOException {
		this.blockId++;

		try {
			this.blockBufferedWriter = new BufferedWriter(new FileWriter(new File(this.blockFilePath + this.blockId + ".txt")));

			for (Map.Entry<String, Map<Integer, Long>> d : dictionary.entrySet()) {
				// term = d.getKey(); posting = d.getValue();
				long collectionFreq = 0; // sum of the termFreq in the dictionary
				int docFreq = d.getValue().size(); // sum of the number of documents in the posting

				// action 1: write dictionary to the block file
				this.blockBufferedWriter.write(d.getKey() + " ");
				for (Map.Entry<Integer, Long> p : d.getValue().entrySet()) {
					// docId = p.getKey(); termFreq = p.getValue();
					this.blockBufferedWriter.write(p.getKey() + ":" + p.getValue() + ",");
					collectionFreq += p.getValue();
				}
				this.blockBufferedWriter.write("\n");

				// action 2: keep records of blockLocation
				if (!this.blockLocation.containsKey(d.getKey())) {
					Queue<Integer> location = new LinkedList<>();
					location.add(this.blockId);
					this.blockLocation.put(d.getKey(), location);
				} else {
					Queue<Integer> location = this.blockLocation.get(d.getKey());
					location.add(this.blockId);
					this.blockLocation.put(d.getKey(), location);
				}

				// action 3: keep recodes of termSum
				long[] sum = new long[2];
				if (!this.termSum.containsKey(d.getKey())) {
					sum[0] = collectionFreq;
					sum[1] = docFreq;
					this.termSum.put(d.getKey(), sum);
				} else {
					sum[0] = termSum.get(d.getKey())[0] + collectionFreq;
					sum[1] = termSum.get(d.getKey())[1] + docFreq;
					this.termSum.put(d.getKey(), sum);
				}
			}
			this.blockBufferedWriter.close(); // must close so the data can be actually written in the file

			// action 4: release memory of dictionary
			this.dictionary.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write blockLocation and termSum to index file
	private void indexWriter() throws IOException {
		try {
			this.blockBufferedWriter = new BufferedWriter(new FileWriter(new File(this.indexFilePath)));

			for (Map.Entry<String, Queue<Integer>> bl : this.blockLocation.entrySet()) {
				// term = bl.getKey(); location = bl.getValue();
				// collectionFreq = this.termSum.get(bl.getKey())[0];
				// docFreq = this.termSum.get(bl.getKey())[1];
				this.blockBufferedWriter.write(bl.getKey() + " " + 
											   this.termSum.get(bl.getKey())[0] + " " + 
											   this.termSum.get(bl.getKey())[1] + " ");

				while (!bl.getValue().isEmpty()) {
					int blockId = bl.getValue().poll();
					this.blockBufferedWriter.write(blockId + ",");
				}
				this.blockBufferedWriter.write("\n");
			}
			this.blockBufferedWriter.close(); // must close so the data can be actually written in the file
			this.blockLocation.clear();
			this.termSum.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
