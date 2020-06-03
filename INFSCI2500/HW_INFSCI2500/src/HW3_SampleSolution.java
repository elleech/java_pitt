import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class HW3_SampleSolution {
    public static void main(String[] args){
        ArrayList<Integer> al = new ArrayList<Integer>();
        LinkedList<Integer> ll = new LinkedList<Integer>();
        
        double start,elapsed;
        
        //add
        start=System.nanoTime();
        for(int i=0;i<1000000;i++){
            al.add((int)Math.floor(100*Math.random()+1));
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("ArrayList add: "+(elapsed/1000000000) +"sec");
        
        start=System.nanoTime();
        for(int i=0;i<1000000;i++){
            ll.add((int)Math.floor(100*Math.random()+1));
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("LinkedList add: "+(elapsed/1000000000) +"sec");
        
        //get
        start=System.nanoTime();
        for(int i=0;i<1000000;i++){
            al.get(i);
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("ArrayList get: "+(elapsed/1000000000) +"sec");
        
        start=System.nanoTime();
        ListIterator<Integer> itr=ll.listIterator();
        while(itr.hasNext()){
            int i=itr.next();
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("LinkedList get: "+(elapsed/1000000000) +"sec");
        

        //remove
        start=System.nanoTime();
        for(int i=999999;i>=0;i--){
            al.remove(i);
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("ArrayList remove: "+(elapsed/1000000000) +"sec");
        
        start=System.nanoTime();
        for(int i=999999;i>=0;i--){
            ll.remove(i);
        }
        elapsed=System.nanoTime()-start;
        
        System.out.println("LinkedList remove: "+(elapsed/1000000000) +"sec");     
    }
}