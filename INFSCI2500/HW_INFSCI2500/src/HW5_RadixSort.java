import java.util.LinkedList;
import java.util.Queue;

public class HW5_RadixSort {

	public static void main(String[] args) {
		int[] a = {85, 3, 19, 43, 20, 55, 42, 21, 91, 85, 73, 29};

		//Print unsorted array.
		for (int i = 0; i < a.length; i++) {System.out.print(a[i] + " ");}
		System.out.println();

		double start = System.nanoTime();
		//Sort the array.
		a = radixSort(a);
		double elapsed = (System.nanoTime() - start)/1000000000;

		//Print sorted array.
		for(int i = 0; i < a.length; i++) {System.out.print(a[i] + " ");}
		System.out.println();
		System.out.println("Time used: " + elapsed);
	} //End Main

	public static int[] radixSort(int[] a){
		//IMPLEMENT ME
		// Create 10 buckets 
		Queue[] bucket = new Queue[10];
		for (int i = 0; i < 10; i++) {bucket[i] = new LinkedList<Integer>();}
		
		// Find the longest number of digits in int[] a
		int num = 0;
		for (int i = 0; i < a.length; i++) {
			int n = (int) (Math.log10(a[i]) + 1);
			if (n > num) {num = n;}
		}
		
		// Inspect every digit and put it in the right bucket then int[] a
		for (int l = 0; l < num; l++) {
			// Traverse int[] a and put each integer into the respective bucket
			for (int i = 0; i < a.length; i++) {
				// int where = digit(a[i], (int) Math.pow(10, l));
				int where = a[i] / (int) Math.pow(10, l) %10;
				bucket[where].add(a[i]);
				//System.out.println(l + ": " + bucket[where] + where);
			}
			
			// Remove integer from bucket to int[] a
			int i = 0;
			for (int where = 0; where < bucket.length; where++) {
				while (!bucket[where].isEmpty()) {
					a[i] = (int) bucket[where].remove();
					i++;
				}
			}
		}
		return a;
	} //End radixSort
	
	// Get every digit from an integer
	public static int digit(int num, int div) {
		return (num/div)%10;
	}

} //End Class