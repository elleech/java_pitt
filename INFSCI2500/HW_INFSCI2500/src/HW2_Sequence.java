import java.util.Scanner;

public class HW2_Sequence<E>{
    protected Object[] data;
    protected int size;

    public HW2_Sequence(int n) throws IllegalArgumentException{
        if(n<0){throw new IllegalArgumentException();}
        this.data=new Object[n];
        this.size=0;
    }

    public int size(){
        return this.size;
    }

    public void append(E element){            
        if(this.size<this.data.length){
            this.data[this.size]=element;
        }
        else{
            Object[] temp=new Object[this.size+1];
            System.arraycopy(this.data, 0, temp, 0, this.size);
            this.data=temp;
            this.data[this.size]=element;
        }
        this.size++;
    }

    public Object get(int k) throws IndexOutOfBoundsException{
            if(k<0 || k>=this.size){
                throw new IndexOutOfBoundsException();}
            return this.data[k];
    }

	public void print(){
		System.out.print("\nCurrent Sequence: ");
		for(int i=0;i<this.size();i++){
            System.out.print(this.get(i)+" ");
        }
		System.out.println();
	}

    public void insert(int index, E newElement){
    	//IMPLEMENT ME
    	Object[] temp = new Object[this.size+1];
    	boolean insertMade = false;
    	if (index < this.size) {
	    	for (int i = 0; i < this.size; i++) {
	    		if (i == index && !insertMade) {
	    			temp[i] = newElement;
	    			insertMade = true;
	    			i--;
	    		} else if (i >= index && insertMade) {
	    			temp[i+1] = this.data[i];
	    		} else {
	    			temp[i] = this.data[i];
	    		}
	    	}
    	}
    	if (index == this.size) {
    		System.arraycopy(this.data, 0, temp, 0, this.size);
    		temp[this.size] = newElement;
    	}
    	this.size++;
        this.data = temp;
    }

	public void delete(int index){
		//IMPLEMENT ME
		Object[] temp = new Object[this.size-1];
    	for (int i = 0; i < this.size; i++) {
    		if (i == index) {
    			continue;
    		} else if (i > index) {
    			temp[i-1] = this.data[i];
    		} else {
    			temp[i] = this.data[i];
    		}
    	}
    	this.size--;
    	this.data = temp;
	}
	
    public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		HW2_Sequence<Integer> s = new HW2_Sequence<Integer>(5);
		s.append(1);
		s.append(2);
		s.append(3);
		s.append(4);
		s.append(5);
		s.print();
		
		//INSERT CONTROL LOOP HERE
		boolean flag = true;
		while (flag) {
			System.out.println("Please enter 1 to insert, 2 to delete, or 3 to quit: ");
			int decision = in.nextInt();
			
			switch (decision) {
				case 1:
					System.out.println("Please enter the value to insert: ");
					int newElement = in.nextInt();
					System.out.println("Please enter the index to insert: ");
					int index = in.nextInt();
					s.insert(index, newElement);
					s.print();
					break;
				case 2:
					System.out.println("Please enter the index to delete: ");
					index = in.nextInt();
					s.delete(index);
					s.print();
					break;
				case 3:
					System.out.println("Goodbye.");
					flag = false;
					break;
				default:
					System.out.println("Please enter a valid integer.\n");
			}
		}
    }   //End main
}   //End class