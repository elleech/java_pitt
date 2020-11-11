## Pitt Information Storage & Retrieval (INFSCI 2140) assignments and final project

### Assignement 1: [Document Collection Processing](https://github.com/elleech/java_pitt/tree/master/INFSCI2140/HW_INFSCI2140_1_src)

- Goal: Process TREC standard format document collections.

- Tasks:

  1. Reading Documents from Collection Files (trectext & trecweb format)

     - PreProcessData.DocumentCollection: The interface for sequentially reading documents from collection files
     - PreProcessData.TrectextCollection: The class for trectext format
     - PreProcessData.TrecwebCollection: The class for trecweb format

  2. Normalize Document Texts

     - PreProcessData.TextTokenizer: The class for sequentially reading words from a sequence of characters
     - PreProcessData.TextNormalizer: The class for transforming each word to lowercase and conducting stemming.
     - PreProcessData.StopwordsRemover: The class for removing stop word. (A stop word list file will be provided)

- Requirements:

  1. Cannot use any Java API library other than the standard JDK
  2. Need to finish processing two collections within 15 minutes

### Assignement 2: [Collection Index Construction](https://github.com/elleech/java_pitt/tree/master/INFSCI2140/HW_INFSCI2140_2_src)

- Goal: Construct searchable index for a document collection. The collection index is based on the inverted file structure. Usually, it has at least the following two components:

  - Dictionary term file
  - Posting file

- Tasks:

  1. Build an Index

     - Indexing.PreProcessedCorpusReader: The class for reading results from HW1
     - Indexing.MyIndexWriter: The class for writing and storing index of a document

  2. Retrieve Posting Lists of Tokens from an Index

     - Indexing.MyIndexReader: The class for reading info of index files

- Requirements:

  1. Cannot use any Java API library other than the standard JDK
  2. Need to finish processing two collections within 5 minutes

### Assignement 3: [Retrieval Models](https://github.com/elleech/java_pitt/tree/master/INFSCI2140/HW_INFSCI2140_3_src)

- Goal: Implement query likelihood language model by using Dirichlet smoothing

- Tasks:

  1. Automatically Translate Topic Statements to Queries

     - Classes.Query: The class of Query
     - Search.ExtractQuery: The class for extracting normalized, tokenized, stopword-removed query

  2. Implementing the Statistical Language Model

     - Search.QueryRetrievalModel: The class for retrieving a list of top N documents by the query likelihood scores
     - IndexingLucene.MyIndexReader: The class of STANDARD JAVA API

- Requirements:

  1. Need to finish 4 query search within 2 minutes
  2. Need a good exploration to the parameter mu

### Assignement 4:

### Final Project:
