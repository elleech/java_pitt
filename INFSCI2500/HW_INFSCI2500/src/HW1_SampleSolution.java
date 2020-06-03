public class HW1_SampleSolution {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 10000; i <= 100000; i += 10000) {
            Long start = System.nanoTime();
            for (int j = i; j > 1; j--) {
                if (isPrime(j)) {
                    count++;
                }
            }
            Long stop = System.nanoTime();
            double duration = (double) (stop - start) / 1000000000;
            System.out.println(i + "\t" + count + "\t" + duration);
            //System.out.println("i="+i+"\tcount="+count+"\tduration="+duration);
            count = 0;
        }
    }

    public static boolean isPrime(int n) {
        int i;
        for (i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}