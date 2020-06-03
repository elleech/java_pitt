public class ArrayPureQueue<E> implements PureQueue<E> {
    protected Object[] data;    //Holds the elements of the queue
    
    //Size is the number of elements in the queue.
    //Head is the index of the front of the queue.
    //Tail is the index of the back of the queue.
    protected int size,head,tail;

    public ArrayPureQueue(){
        data = new Object[10];
        head=0;
        tail=-1;
        size=0;
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void enqueue(E element) {
        //If the array is full, expand it.
        if(size==data.length){
            //Copy the current data array to a temp array.
            E[] temp = (E[])data;
            
            //Assign data to a new array of 2x data.length
            data=(E[])new Object[data.length*2];
            
            //Copy temp to data, starting from head to length-1
            System.arraycopy(temp, head, data, 0, temp.length-head);
            
            //If the queue has become circular,
            if(head>0){
                //Continue copying from the beginning of temp to tail.
                //The starting point for this portion maps to the new array at
                //index of temp.length-head.  We copy tail+1 items.
                System.arraycopy(temp,0,data,temp.length-head,tail+1);
            }
            head=0; //head is now the beginning of the array
            tail=temp.length-1; //tail is now the position of the last element
        }
        
        tail=(tail+1)%data.length;  //Increment tail mod data.length.
        size++; //Increment size.
        data[tail]=element; //Last, put the new element at index tail.
    }

    public E dequeue() {
        E result=(E)data[head];
        head=(head+1)%data.length;
        size--;
        if(size==0){
            head=0;
            tail=-1;
        }
        return result;
    }

    public E front() {
        return (E)data[head];
    }
    
    public static void main(String[] args){
        //Create a queue, add to it til it needs to expand, then remove til it is empty.
        //Print out head,tail,size,length values along the way 
        //Only for demonstration purposes!
        
        ArrayPureQueue<Integer> myQueue = new ArrayPureQueue<Integer>();
        
        System.out.println("Enqueueing...");
        for(int i=0;i<11;i++){
            myQueue.enqueue(i);
            System.out.println("\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        }
        
        System.out.println("\nDequeueing...");
        while(!myQueue.isEmpty()){
            System.out.println("Element: "+myQueue.dequeue()+"\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        }
        
        System.out.println("\n\nPart 2");
        //Fill it to capacity, then remove one, then add two more, then dequeue the whole thing.
        myQueue = new ArrayPureQueue<Integer>();
        
        System.out.println("Enqueueing...");
        for(int i=0;i<10;i++){
            myQueue.enqueue(i);
            System.out.println("\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        }
        
        //Remove one
        System.out.println("Element: "+myQueue.dequeue()+"\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        
        //Add one
        myQueue.enqueue(10);
        System.out.println("\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        
        //Add one
        myQueue.enqueue(11);
        System.out.println("\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        
        //Dequeue all
        System.out.println("\nDequeueing...");
        while(!myQueue.isEmpty()){
            System.out.println("Element: "+myQueue.dequeue()+"\thead: "+myQueue.head+"\ttail: "+myQueue.tail+"\tsize: "+myQueue.size+"\tlength: "+myQueue.data.length);
        }
    }
}
