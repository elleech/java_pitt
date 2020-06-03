import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<E> implements PurePriorityQueue<E> {
    public static final int DEFAULT_INITIAL_CAPACITY=11;
    protected ArrayList<E> list;    //Holds the elements of the heap.
    protected Comparator<E> comparator;
    
    public Heap(int initialCapacity,Comparator<E> comp){
        list=new ArrayList(initialCapacity);
        comparator=comp;
    }
    
    public Heap(){
        this(DEFAULT_INITIAL_CAPACITY,null);
    }
        
    public Heap(int initialCapacity){
        this(initialCapacity,null);
    }

    public Heap(Comparator<E> comp){
        this(DEFAULT_INITIAL_CAPACITY,comp);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    //Add a new element to the back of the ArrayList
    //Then call percolateUp to maintain heap properties.
    public void add(E element) {
        list.add(element);
        //See intermediate situation in example 3.
        percolateUp(); 
    }
    
    //Return the root element.
    public E getMin() {
        return list.get(0);
    }

    //Delete the root element.
    //This opens a hole in the tree.   Which element should be used to fill it?
    //One of its children? No, use element at the end of the list. (example 5,6)
    //This preserves the complete binary tree property of a heap.
    //Then handle ordering issues with percolateDown.
    public E removeMin() {
        //Store the value of the root element.
        E minElement=list.get(0);
        //Set the root element value to the value of the last element in the list.
        list.set(0, list.get(list.size()-1));
        //Remove the last element from the list.
        list.remove(list.size()-1);
        //Handle any ordering issues.
        percolateDown(0);
        //Return the removed root element value.
        return minElement;
    }
    
    //Handles restoring ordered heap property to complete binary tree after a removal.
    //Example 6.
    //Compare the element at the starting position to its children.
    //If the element is greater than its children, swap with the smallest child.
    //Child elements in the list for index i are at 2i+1 (left), 2i+2 (right).
    protected void percolateDown(int start){
        //Indexes of the parent and its left child.
        int parent=start,child=(parent*2)+1;
        
        //Continue if the child index is within the size of the list.
        while(child<list.size()){
            //If there is also a right child and the left child is greater than the right child,
            if(child<list.size()-1 && compare(list.get(child),list.get(child+1))>0){
                child++;    //Increment child to be the smaller child index.
            }
            //If the smaller child of the parent is greater than or equal to the parent,
            if(compare(list.get(child),list.get(parent))>=0){
                break;  //Do nothing, this is what we want.
            }
            //Otherwise, swap the parent and the smaller child.
            swap(parent,child);
            //Set the parent index to the child's old index.
            parent=child;
            //Update child to be the index of the left child of parent's new position.
            child=(parent*2)+1; 
        }
    }
    
    //Handles restoring ordered heap property to complete binary tree after an add.
    //Example 3,4.
    //The added element might not be in the right place such that if the 
    //ArrayList were a binary tree that the added element is greater than its parent.
    //Starts at the back of ArrayList and swaps with that element's parent if it is less than the parent.
    //Element's parent can be found at (index-1)/2. (int division)
    protected void percolateUp(){
        //To begin with, the new element is added to the end of the list.
        int child=list.size()-1,parent;
        
        //So long as the new element is not the root element,
        while(child>0){
            parent=(child-1)/2; //Calculate the position of its parent.
            //See if the child is greater or equal to the parent.
            if(compare(list.get(child),list.get(parent))>=0){
                break;  //If so, we stop.
            }
            //Otherwise, swap the parent and child.
            swap(parent,child);
            //Then we loop again to check to see if the child is ok in its new position.
            child=parent;
        }
    }
    
    //A helper method that directs which ordering system to use.
    private int compare(E element1, E element2){
        //In this line we use the ? operator.  It is used as follows:
        //<CONDITIONAL> ? <DO THIS IF TRUE> : <DO THIS IF FALSE>
        //In this case, we ask if there is a custom ordering system for this heap.
        //If the comparator field is null, there is not, so we use compareTo for whatever class the elements are.
        //If it is not null, then we use the compare method we have written to do the comparison.
        return (comparator==null ? ((Comparable)element1).compareTo(element2) : comparator.compare(element1, element2));
    }
    
    //Swaps elements in an arraylist.
    protected void swap(int parent,int child){
        //Set the value of one element to a temporary variable.
        E temp=list.get(parent);
        //Set the position of one element to the value of the other.
        list.set(parent,list.get(child));
        //Set the position of the other to the temp variable.
        list.set(child,temp);
    }
    
    //Move the contents of a list into a heap then back 
    //into the list one element at a time.
    //Afterwards the list will be in order.
    public static <E> void heapSort(List<E> aList){
        //Create a heap with capacity the same as the size of the list.
        Heap<E> aHeap = new Heap<E>(aList.size());
        
        //For each element in the list,
        for(E element:aList){
            //Add it to the heap.
            aHeap.add(element);
        }
        
        //Clear the list.
        aList.clear();
        
        //Copy the elements one at a time back to the list.  
        //The heap can only remove the min element.
        while(!aHeap.isEmpty()){
            aList.add(aHeap.removeMin());
        }
        //The list is now sorted.
    }
    
    public static void main(String[] args){
        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(88);
        scores.add(55);
        scores.add(77);
        scores.add(55);
        scores.add(91);
        System.out.println("Unsorted list: "+scores);
        heapSort(scores);
        System.out.println("Sorted list: "+scores);
    }
}
