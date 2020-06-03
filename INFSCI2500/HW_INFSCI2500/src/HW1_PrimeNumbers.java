import java.io.FileNotFoundException;
import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.List;

public class HW1_PrimeNumbers {

	public static void main(String[] args) throws FileNotFoundException {
		int[] n = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
		PrintWriter saveFile = new PrintWriter("HW1_PrimeNumbers.txt");
		for (int step: n) {
			int count = 0;
			long t1 = System.nanoTime();
			count = countPrime(step);
			long t2 = System.nanoTime();
			double countTime = (double) (t2-t1)/1000000000;
			System.out.println(step + ", " + count + ", " + countTime);
			saveFile.write(step + "\t" + count + "\t" + countTime + "\n");
		}
		saveFile.close();
	}
	
	// Adapted from https://www.programcreek.com/2014/04/leetcode-count-primes-java/
	public static int countPrime(int n) {
		// Using ArrayList to keep record of prime numbers for future requirement if there's any
		// List<Integer> prime = new ArrayList<Integer>();
		int count = 0; // a little bit faster than using list
		for (int i = 2; i <= n; i++) {
			boolean isPrime = true;
			for (int j = 2; j*j <= i; j++) {
				if (i%j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				// prime.add(i);
				count++;
			}
		}
		// return prime.size();
		return count;
	}
	
	/*
	public static long countTime(int n) {
		long t1 = System.nanoTime();
		countPrime(n);
		long t2 = System.nanoTime();
		return t2-t1;
	}
	*/

}
