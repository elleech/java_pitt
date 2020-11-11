# INFSCI 2140 HW2

by YEC24

## Concept

### Indexing.PreProcessedCorpusReader:

- Constructor: Init BufferedReader
- NextDocument: Retrun a HashMap of docNo-content pair

### Indexing.MyIndexWriter:

- Constructor: Init BufferedWriter, docId (int), blockId (int), dictionary (HashMap <token, posting>), blockLocation (HashMap <token, blockId>), termSum (HashMap <token, [collectionFreq, docFreq]>)
- IndexADocument: Retrun a HashMap of DOCNO-TEXT pair
- blockWriter: Write dictionary to block file, keep records of blockLocation and termSum, and release the memory of dictionary
- indexWriter: Write blockLocation and termSum to index file
- Close: Close BufferedWriter

### Indexing.MyIndexReader:

- Constructor: Init BufferedReader, blockLocation, termSum
- GetDocid: Return document id
- GetDocno: Return document number
- GetPostingList: Return token posting (int[][] [docid, freq])
- GetDocFreq: Return the number of documents that contains the token
- GetCollectionFreq: Return the total number of times the token appears in the collection
- getRecord: Store data to blockLocation and termSum
- Close: Close BufferedWriter

## Environment Configuration

- Windows 10 x64
- Eclipse IDE for Java Developers 2020-06
- Intel Core i5-8265U
- RAM 12.0GB
- jdk1.8.0_221
- jre1.8.0_261

## Run Time

- trecweb: 0.42 min (index corpus) + 0.014 min (load index & retrieve token)
- trectext: 1.42 min (index corpus) + 0.018 min (load index & retrieve token)

## Results

finish 30000 docs

finish 60000 docs

finish 90000 docs

finish 120000 docs

finish 150000 docs

finish 180000 docs

NullPointerException thrown!

totaly document count: 198361

index web corpus running time: 0.42138333333333333 min

> > the token "acow" appeared in 3 documents and 3 times in total

       lists-092-3952951    154963         1

      lists-108-11347927    186006         1

       lists-092-4113429    154964         1

load index & retrieve running time: 0.014183333333333333 min

finish 30000 docs

finish 60000 docs

finish 90000 docs

finish 120000 docs

finish 150000 docs

finish 180000 docs

finish 210000 docs

finish 240000 docs

finish 270000 docs

finish 300000 docs

finish 330000 docs

finish 360000 docs

finish 390000 docs

finish 420000 docs

finish 450000 docs

finish 480000 docs

NullPointerException thrown!

totaly document count: 503473

index text corpus running time: 1.4195 min

> > the token "yhoo" appeared in 5 documents and 5 times in total

        NYT19990208.0397    291085         1

        NYT20000717.0201    477373         1

        NYT20000927.0406    502701         1

        NYT19990405.0253    313384         1

        NYT20000928.0343    503146         1

load index & retrieve running time: 0.01765 min
