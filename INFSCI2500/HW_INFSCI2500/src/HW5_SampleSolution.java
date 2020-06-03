import java.util.ArrayList;

/**
 *
 * @author garrardw
 */
public class HW5_SampleSolution {
    public static void main(String[] args) {
        int[] a = {85,3,19,43,8888,20,55,42,21,91,85,73,29,777};
        
        //Print unsorted array.
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        
        double start = System.nanoTime();
        //Sort the array.
        a=radixSort(a);
        double elapsed = (System.nanoTime()-start)/1000000000;
        
        //Print sorted array.
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println("\nelapsed time: "+elapsed+" s");
    }
    
    public static int[] radixSort(int[] myArray){
        final int radix=10;
        //0. Prepare sorting structure, an array list of queues.
        ArrayList<ArrayPureQueue<Integer>> sortingList = new ArrayList<>();
        for(int i=0;i<radix;i++){
            sortingList.add(new ArrayPureQueue<>());
        }
        
        //1. Find number of digits in array element with most digits.
        int maxDigits=0;
        for(int i=0;i<myArray.length;i++){
            int currentDigits=(int)Math.floor(Math.log(myArray[i])/Math.log(radix))+1;
            if(currentDigits>maxDigits){maxDigits=currentDigits;}
        }
        
        //2. Outer loop for maxDigits iterations.
        for(int i=0;i<maxDigits;i++){
            //3. First inner loop for each element of the collection.
            for(int j=0;j<myArray.length;j++){
                //4. Get the relevant digit of the current element.
                int currentDigit=(int)(myArray[j] / Math.pow(radix,i)) % radix;
                //5. Enqueue the current element at the queue in the sorting structure located at index current digit.
                sortingList.get(currentDigit).enqueue(myArray[j]);
            }//End first inner loop.
            
            //6. Second inner loop for each queue in the sorting structure.
            for(int j=0,arrayIndex=0;j<sortingList.size();j++){
                ArrayPureQueue<Integer> currentQueue = sortingList.get(j);
                //7. Dequeue the current queue back into the array.
                while(!currentQueue.isEmpty()){
                    myArray[arrayIndex]=currentQueue.dequeue();
                    arrayIndex++;
                }
            }//End second inner loop.
        }//End outer loop.
        return myArray;
    }//End method.
}//End class.
