package lab12;

public class Recursion {

	public static void main(String[] args) {
		
		System.out.println(sumOfDigits(-234));
		System.out.println(sumOfDigits(-12));
		System.out.println(sumOfDigits(-39));
		System.out.println();

		int a[] = {1, 2, 3, 4, 5};
		printArrayElements(a, 0);
		
		int[] b = {2, 3, 7, 8, 9};
		printCombos(b, "", 1, 3);

	}

	// Task 1
	public static int sumOfDigits(int x) {
		if (x == 0) {
			return 0;
		} else { 
			return Math.abs(x)%10 + sumOfDigits(Math.abs(x)/10);
		}
	}

	// Task 2
	public static void printArrayElements(int a[], int index) {
		if (index < a.length) {
			System.out.println(a[index]);
			printArrayElements(a, index+1);
		} else {
			System.out.println();
		}
	}

	// Task 3
	// https://www.techiedelight.com/find-distinct-combinations-of-given-length/
	public static void printCombos(int[] a, String out, int startIndex, int k) {
		// Base case: Print out when it has k numbers of integers
		if (k == 0) {
			System.out.println(out);
			return;
		}

		// Iterate from the startIndex till the end of array
		for (int i = startIndex; i < a.length; i++) {
			// Add current element a[i] to out
			// Recur for the next index (i+1) with one less element (k-1)
			printCombos(a, out+a[i]+" ", i+1, k-1);
		}
		
		/* Demo example:
		 * printCombos({2, 3, 7, 8, 9}, "", 1, 3);
		 * 
		 * k=3, i=1 -> printCombos(a, "3 ", 2, 2)
		 * 			-> k=2, i=2 -> printCombos(a, "3 7 ", 3, 1)
 		 * 						-> k=1, i=3 -> printCombos(a, "3 7 8 ", 4, 0)
 		 * 									-> k=0, i=4 -> System.out.println("3 7 8 ")
 		 * 						-> k=1, i=4 -> printCombos(a, "3 7 9 ", 4, 0)
 		 * 									-> k=0, i=4 -> System.out.println("3 7 9 ")
 		 * 			-> k=2, i=3 -> printCombos(a, "3 8 ", 4, 1)
 		 * 						-> k=1, i=4 -> printCombos(a, "3 8 9 ", 5, 0)
 		 * 									-> k=0, i=5 -> System.out.println("3 8 9 ")
 		 * 						-> k=1, i=5 -> <END FOR LOOP>
 		 * 			-> k=2, i=4 -> printCombos(a, "3 9 ", 5, 1)
 		 * 						-> k=1, i=5 -> <END FOR LOOP>
 		 * k=3, i=2 -> printCombos(a, "7 ", 3, 2)
 		 * 			-> k=2, i=3 -> printCombos(a, "7 8 ", 4, 1)
 		 * 						-> k=1, i=4 -> printCombos(a, "7 8 9 ", 5, 0)
 		 * 									-> k=0, i=5 -> System.out.println("7 8 9 ")
 		 * 						-> k=1, i=5 -> <END FOR LOOP>
 		 * k=3, i=3 -> printCombos(a, "8 ", 4, 2)
 		 * 			-> k=2, i=4 -> printCombos(a, "8 9 ", 5, 1)
 		 * 						-> k=1, i=5 -> <END FOR LOOP>
 		 * k=3, i=4 -> printCombos(a, "9 ", 5, 2)
 		 * 			-> k=2, i=5 -> <END FOR LOOP>
		 */
	}

}
