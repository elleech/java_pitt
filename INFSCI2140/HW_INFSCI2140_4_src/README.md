# INFSCI 2140 HW4

by YEC24

This assignment is for implementing the relevance feedback model. We use Lucene API to obtain top K relevant documents as our pseudo feedback document, then get top N final relevant documents based on the relevance feedback model.

## Concept

### [PseudoRFSearch.PseudoRFRetrievalModel](https://github.com/elleech/java_pitt/blob/master/INFSCI2140/HW_INFSCI2140_4_src/PseudoRFSearch/PseudoRFRetrievalModel.java):

- Constructor: Init MyIndexReader, QueryRetrievalModel, and the total collection length
- RetrieveQuery: Return a list of **top N** documents by modifying the query likelihood scores (relevant scores) with the pseudo RF score.
  - Original token score: P(w|D) = ( C(w, D) + mu\*P(w|ref) ) / ( |D| + mu )
  - Considering pseudo RF: P'(w|D) = alpha\*P(w|D) + (1-alpha)\*P(w|feedbackDocs)
  - Total document score: P(Q|D) = P'(w1|D)\*P'(w2|D)\*...
- GetTokenRFScore: Return a HashMap of token-score pair based on **top K** relevant documents
  - P(w|feedbackDocs) = ( C(w, feedbackDocs) + mu\*P(w|ref) ) / ( |feedbackDocs| + mu )
  - Need to treat all feedbackDocs as one big pseudo document

### [IndexingLucene.MyIndexReader](https://github.com/elleech/java_pitt/blob/master/INFSCI2140/HW_INFSCI2140_4_src/IndexingLucene/MyIndexReader.java):

- STANDARD JAVA API provided by instructor
- TokenFreq: Return the number of times the token appears in the appointed document
- collectionLength: Return the length of the collection

## Environment Configuration

- Windows 10 x64
- Eclipse IDE for Java Developers 2020-06
- Intel Core i5-8265U
- RAM 12.0GB
- jdk-11.0.8
- jre1.8.0_261
- AdoptOpenJDK with Hotspot 11.0.8.10
- latest Lucene library (tried to solve the warning but no use)

## Run Time

- About 0.6 min

## Result

(Ignored illegal reflective access of lucene warnings):

901 Q0 NYT19990114.0111 1 0.02661332818051991 MYRUN

901 Q0 NYT20000715.0019 2 0.025569906459399428 MYRUN

901 Q0 NYT19980923.0086 3 0.02532447592281584 MYRUN

901 Q0 NYT19980923.0149 4 0.02532447592281584 MYRUN

901 Q0 NYT19990312.0452 5 0.025126441879453832 MYRUN

901 Q0 NYT19990110.0053 6 0.024976490421027463 MYRUN

901 Q0 NYT20000410.0370 7 0.024680330083399492 MYRUN

901 Q0 XIE19960603.0042 8 0.024675335561045834 MYRUN

901 Q0 NYT19990211.0382 9 0.02455602607071058 MYRUN

901 Q0 XIE19960114.0024 10 0.024499969194042598 MYRUN

901 Q0 XIE19970523.0048 11 0.024490422428986667 MYRUN

901 Q0 XIE19960823.0163 12 0.024473622274778936 MYRUN

901 Q0 XIE19960823.0168 13 0.02447275452203293 MYRUN

901 Q0 XIE19961106.0183 14 0.024454064584695193 MYRUN

901 Q0 XIE19960630.0135 15 0.024429361107417533 MYRUN

901 Q0 XIE19960519.0065 16 0.024309041916698203 MYRUN

901 Q0 NYT19990511.0247 17 0.024307352609644613 MYRUN

901 Q0 XIE19960326.0091 18 0.024288970164664987 MYRUN

901 Q0 XIE19961216.0011 19 0.024285841872082735 MYRUN

901 Q0 NYT19980925.0154 20 0.02421818859512713 MYRUN

902 Q0 NYT19991112.0257 1 0.01603077528144656 MYRUN

902 Q0 NYT19980605.0288 2 0.014882399882537558 MYRUN

902 Q0 NYT19990128.0291 3 0.01410399818579678 MYRUN

902 Q0 NYT19990204.0001 4 0.013974734310294404 MYRUN

902 Q0 NYT19990315.0372 5 0.013681997464900078 MYRUN

902 Q0 NYT19980805.0492 6 0.013652033748507025 MYRUN

902 Q0 NYT19990825.0363 7 0.013596605680607641 MYRUN

902 Q0 NYT19990804.0421 8 0.013502204355767206 MYRUN

902 Q0 NYT20000608.0342 9 0.013435103271711914 MYRUN

902 Q0 NYT19980714.0366 10 0.013396305587190746 MYRUN

902 Q0 NYT19980714.0368 11 0.013396305587190746 MYRUN

902 Q0 NYT19990811.0457 12 0.01334477378834394 MYRUN

902 Q0 NYT19990122.0274 13 0.013328167207306844 MYRUN

902 Q0 NYT19981117.0254 14 0.01328945980011665 MYRUN

902 Q0 NYT20000114.0202 15 0.01325762278305455 MYRUN

902 Q0 NYT19990831.0134 16 0.013239486010146354 MYRUN

902 Q0 NYT19990901.0132 17 0.013239486010146354 MYRUN

902 Q0 NYT19990212.0073 18 0.013110231113140428 MYRUN

902 Q0 NYT19981113.0332 19 0.013109263546771423 MYRUN

902 Q0 NYT19980923.0398 20 0.013077945582439104 MYRUN

903 Q0 NYT20000501.0225 1 0.018682208533076824 MYRUN

903 Q0 NYT20000424.0197 2 0.01649841029081469 MYRUN

903 Q0 NYT19980719.0183 3 0.01647633080466455 MYRUN

903 Q0 NYT19981112.0231 4 0.016286099091042675 MYRUN

903 Q0 NYT19990812.0218 5 0.016258823695136645 MYRUN

903 Q0 NYT19981019.0221 6 0.015947969170753317 MYRUN

903 Q0 NYT19990425.0128 7 0.015641267433671835 MYRUN

903 Q0 NYT19990805.0168 8 0.015584550544818004 MYRUN

903 Q0 NYT19990812.0181 9 0.015547657896833843 MYRUN

903 Q0 NYT20000708.0131 10 0.015531539567012025 MYRUN

903 Q0 NYT19990505.0086 11 0.01549979007694784 MYRUN

903 Q0 NYT19990504.0190 12 0.01549841029081469 MYRUN

903 Q0 NYT19990114.0134 13 0.015497160681317658 MYRUN

903 Q0 NYT19981119.0287 14 0.015449971794957457 MYRUN

903 Q0 NYT20000422.0243 15 0.015428354758990685 MYRUN

903 Q0 NYT19990608.0192 16 0.015370939536478265 MYRUN

903 Q0 NYT19990730.0181 17 0.015369378032750175 MYRUN

903 Q0 NYT20000302.0188 18 0.015366680024217722 MYRUN

903 Q0 NYT19991118.0423 19 0.015355590379425585 MYRUN

903 Q0 NYT19991104.0270 20 0.01535486449542101 MYRUN

904 Q0 XIE19961013.0080 1 0.021228671391269865 MYRUN

904 Q0 XIE19961010.0123 2 0.02056202200079324 MYRUN

904 Q0 XIE19961013.0085 3 0.020408763663855148 MYRUN

904 Q0 XIE19970302.0144 4 0.019785384848184467 MYRUN

904 Q0 XIE19961105.0192 5 0.019749182387936777 MYRUN

904 Q0 XIE19961014.0209 6 0.019719017551284523 MYRUN

904 Q0 XIE19971225.0050 7 0.019566303823164946 MYRUN

904 Q0 XIE19961010.0139 8 0.01954684721834249 MYRUN

904 Q0 XIE19961010.0138 9 0.019529226670072352 MYRUN

904 Q0 NYT19981227.0058 10 0.019495639872737048 MYRUN

904 Q0 XIE19961011.0170 11 0.019482549084956665 MYRUN

904 Q0 NYT20000526.0209 12 0.019417732378483845 MYRUN

904 Q0 NYT20000716.0169 13 0.019403158379001473 MYRUN

904 Q0 NYT20000906.0313 14 0.019393166566736526 MYRUN

904 Q0 XIE19961105.0131 15 0.019385497750062326 MYRUN

904 Q0 XIE19961105.0166 16 0.019356878651990157 MYRUN

904 Q0 XIE19961010.0120 17 0.01935153937030152 MYRUN

904 Q0 XIE19961010.0117 18 0.019349769406585778 MYRUN

904 Q0 NYT19990731.0069 19 0.019283021749037565 MYRUN

904 Q0 NYT19990802.0058 20 0.019283021749037565 MYRUN

4 queries search time: 0.5963833333333334 min