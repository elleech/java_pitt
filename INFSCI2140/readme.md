## Pitt Information Storage & Retrieval (INFSCI 2140) assignments and final project

### Assignement 1: [Document Collection Processing](https://github.com/elleech/java_pitt/tree/master/INFSCI2140/HW_INFSCI2140_1/src)

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

### Assignement 2: [Collection Index Construction](https://github.com/elleech/java_pitt/tree/master/INFSCI2140/HW_INFSCI2140_2/src)

- Goal: Construct searchable index for a document collection. The collection index is based on the inverted file structure. Usually, it has at least the following two components:

  - Dictionary term file
  - Posting file

- Tasks:

  1. Build an index

  - Indexing.PreProcessedCorpusReader: The class for reading results from HW1
  - Indexing.MyIndexWriter: The class for writing and storing index of a document

  2. Retrieve posting lists of tokens from an index

  - Indexing.MyIndexReader: The class for reading info of index files

- Requirements:

  1. Cannot use any Java API library other than the standard JDK
  2. Need to finish processing two collections within 5 minutes

### Assignement 3:

### Assignement 4:

### Final Project:
