import java.text.DecimalFormat;
import java.util.Scanner;

public class HW6_Huffman {
	protected Entry[] leafEntries;  //Stores the chars in the Huffman tree.
	public final static int SIZE = 256; //The number of allowable chars, the ASCII set.
	//Also, we can store char information at the index relating to its decimal ASCII value.
	//E.g., "B" would be stored at index (int)'B' = 66 in leafEntries.

	protected PurePriorityQueue<Entry> pq;  //A PQ object for use in the Huffman algorithm.

	public HW6_Huffman(){
		//Fill up the leafEntries arrays with empty Entries.
		Entry entry;
		leafEntries = new Entry[SIZE];  //The size of the array will never be modified because the character set is fixed length.
		for(int i=0;i<SIZE;i++){
			//At each index in the array,
			leafEntries[i] = new Entry();   //Create a new Entry object.
			//Then give that Entry some default data.
			entry=leafEntries[i];
			entry.freq=0;
			entry.left=null;
			entry.right=null;
			entry.parent=null;
		}
		//Then initialize the PQ.
		pq=new Heap<Entry>();
	}

	//Updates the frequencies of the characters in the message.
	public void updateFrequencies(String message){
		Entry entry;
		//Move through the message one character at a time.
		for(int j=0;j<message.length();j++){
			//Access the entry object in the array at index corresponding to the char's decimal ASCII value.
			entry=leafEntries[(int)(message.charAt(j))];
			//Increment that entry's frequency.
			entry.freq++;
		}
	}

	//Creates the priority queue from the frequencies in the leafEntries array.
	public void createPQ(){
		Entry entry;
		//Move through the leafEntries array.
		for(int i=0;i<SIZE;i++){
			entry=leafEntries[i];
			//If the current entry in the array has frequency greater than zero,
			if(entry.freq>0){
				//Add that entry to the PQ.
				pq.add(entry);
			}
		}
	}

	//Creates the Huffman tree
	//Until the PQ consists of a single entry, a pair of entries is removed at a time.
	//Those entries become the left and right children of an entry that contains the sum of the pair's frequencies.
	//Then the sum entry is added to the PQ.
	public void createHuffmanTree(){
		Entry left,right,sum;
		while(pq.size()>1){
			left=pq.removeMin();
			left.code="0";  //Left branch always is a 0 bit.
			right=pq.removeMin();
			right.code="1"; //Right branch always is a 1 bit.

			sum=new Entry();
			sum.parent=null;    //This entry will be the root.
			//Proceed to link the new entries together in the proper way.
			sum.freq=left.freq+right.freq;
			sum.left=left;
			sum.right=right;
			left.parent=sum;
			right.parent=sum;
			pq.add(sum);
		}
	}

	//Calculates the Huffman codes for each character with freq>0.
	//For each such character in leafEntries,
	//Start with an empty String c.
	//Prepend the code field for the char entry we start at to c. (Put it in front)
	//Move to the parent of the current entry.
	//Prepend code there to c.
	//Stop when we get to the root.
	//Then assign c to the code field for that char's entry in leafEntries.
	public void calculateHuffmanCodes(){
		//IMPLEMENT ME
		String c;
		Entry entry;
		for (int i = 0; i < SIZE; i++) {
			c = "";
			entry = leafEntries[i];
			if (entry.freq > 0) {
				while (entry.parent != null) {
					c = entry.code + c;
					entry = entry.parent;
				}
				leafEntries[i].code = c;
			}
		}
	}

	//Constructs and returns a String which contains the chars in the message and their Huffman codes.
	public String getCodes(){
		Entry entry;
		String codes=new String();
		for(int i=0;i<SIZE;i++){
			entry = leafEntries[i];
			if(entry.freq>0){
				codes += (char)i + " " + entry.code + "\n";
			}
		}
		return codes;
	}

	//Transform the message into a coded message.
	public String getEncodedMessage (String message){
		Entry entry;
		String encodedMessage = new String();

		//Move along the message character by character.
		for(int j=0;j<message.length();j++){
			//For each character in the message, get the entry corresponding to that character.
			entry=leafEntries[(int)(message.charAt(j))];
			//Append the code for that character to the encoded message String.
			encodedMessage+=entry.code;
		}       
		return encodedMessage;
	}

	public static void main(String[] args){
		//Get in a message from the keyboard.
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a message to encode >");
		String message = s.nextLine();

		//Encode the message and print it out.
		HW6_Huffman h = new HW6_Huffman();
		h.updateFrequencies(message);
		h.createPQ();
		h.createHuffmanTree();
		h.calculateHuffmanCodes();
		String codes = h.getCodes();
		String encodedMessage = h.getEncodedMessage(message);
		double ratio = (((double)(message.length()*8)-(double)encodedMessage.length())/(double)(message.length()*8))*100;
		DecimalFormat df = new DecimalFormat(".##");   //A formatter for the ratio double value which shows 2 decimal places.

		System.out.println("\nHuffman codes for this message: \n"+codes);
		System.out.println("Encoded message: "+encodedMessage);

		//256 characters means log2(256) bits per character for fixed-width encoding.
		System.out.println("Fixed-width encoding of this message would require "+message.length()*8+" bits.");
		System.out.println("After Huffman encoding, the message takes up "+encodedMessage.length()+" bits, "
				+ "a savings of "+df.format(ratio)+"%");
	}
}

//A custom entry class for elements in the Huffman tree.
//Includes an overridden definition of the compareTo method 
//so elements can be directly compared on the basis of character frequency.
//No type parameter, elements are always a char.
class Entry implements Comparable{
	int freq;       //The frequency for this char in the message.
	String code;    //The Huffman encoding bit for this entry.
	char id;        //The char itself.
	Entry left,right,parent;

	public int compareTo(Object entry){
		return freq-((Entry)entry).freq;
	}
}
