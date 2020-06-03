import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class HW3_ListComparison {

	public static void main(String[] args) {
		int size = 1000000;
		long ts, te;
		ArrayList<Integer> al = new ArrayList<>();
		LinkedList<Integer> ll = new LinkedList<>();
		
		// Time of ArrayList.add()
		ts = System.nanoTime();
		for (int i = 0; i < size; i++) {al.add(i);}
		te = System.nanoTime();
		System.out.println("ArrayList add:  " + (te-ts)/1000000000.0 + " sec.");
		
		// Time of LinkedList.add()
		ts = System.nanoTime();
		for (int i = 0; i < size; i++) {ll.add(i);}
		te = System.nanoTime();
		System.out.println("LinkedList add: " + (te-ts)/1000000000.0 + " sec.");
		
		System.out.println("------------------------------------------");
		
		// Time of ArrayList.get()
		ts = System.nanoTime();
		for (int i = 0; i < size; i++) {al.get(i);}
		te = System.nanoTime();
		System.out.println("ArrayList get:  " + (te-ts)/1000000000.0 + " sec.");
		
		// Time of LinkedList.get()
		ListIterator<Integer> itr = ll.listIterator();
		ts = System.nanoTime();
		while(itr.hasNext()) {itr.next();}
		te = System.nanoTime();
		System.out.println("LinkedList get: " + (te-ts)/1000000000.0 + " sec.");
		
		System.out.println("------------------------------------------");
		
		// Time of ArrayList.remove()
		ts = System.nanoTime();
		for (int i = size-1; i >= 0; i--) {al.remove(i);}
		te = System.nanoTime();
		System.out.println("ArrayList remove:  " + (te-ts)/1000000000.0 + " sec.");
		
		// Time of LinkedList.remove()
		itr = ll.listIterator();
		ts = System.nanoTime();
		while(itr.hasNext()) {
			itr.next();
			itr.remove();
		}
		te = System.nanoTime();
		System.out.println("LinkedList remove: " + (te-ts)/1000000000.0 + " sec.");
	}

}
