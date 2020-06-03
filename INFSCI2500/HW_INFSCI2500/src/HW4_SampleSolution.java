//A simple binary tree class.  
//Does not do anything fancy other than imitate the abstract binary tree data type.
public class HW4_SampleSolution {
    public Object value;                    //The value of an element.
    private HW4_SampleSolution leftChild;     //The element's left child.
    private HW4_SampleSolution rightChild;    //The element's right child.
    
    //Create an element and assign it a value and two children.
    public HW4_SampleSolution(Object value, HW4_SampleSolution leftChild, HW4_SampleSolution rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    //Leaf constructor.
    //Create an element and assign it no children.
    public HW4_SampleSolution(Object value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
    
    public Object getValue() {
        return value;
    }
    
    public HW4_SampleSolution getLeftChild() {
        return leftChild;
    }
    
    public HW4_SampleSolution getRightChild() {
        return rightChild;
    }
    
    public void setLeftChild(HW4_SampleSolution subtree){
        this.leftChild=subtree;
    }
    
    public void setRightChild(HW4_SampleSolution subtree){
        this.rightChild=subtree;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    //If an element's children are null, it is a leaf.
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
    
    public static void inOrder(HW4_SampleSolution t){
	if(t!=null){
            inOrder(t.getLeftChild());
            System.out.print(t.getValue()+", ");
            inOrder(t.getRightChild());
	}
    }
    public static void postOrder(HW4_SampleSolution t){
        if(t!=null){
            postOrder(t.getLeftChild());
            postOrder(t.getRightChild());
            System.out.print(t.getValue()+", ");
	}
    }
    public static void preOrder(HW4_SampleSolution t){
        if(t!=null){
            System.out.print(t.getValue()+", ");
            preOrder(t.getLeftChild());
            preOrder(t.getRightChild());
	}
    }
    public static void breadthFirst(HW4_SampleSolution t){
        HW4_LinkedListPureQueue<HW4_SampleSolution> myQ = new HW4_LinkedListPureQueue<HW4_SampleSolution>();
        HW4_SampleSolution tree = new HW4_SampleSolution(null);
        
        if(t!=null){
            myQ.enqueue(t);
            while(!myQ.isEmpty()){
                tree=myQ.dequeue();
                System.out.print(tree.getValue()+", ");
                if(tree.getLeftChild()!=null){
                    myQ.enqueue(tree.getLeftChild());
                }
                if(tree.getRightChild()!=null){
                    myQ.enqueue(tree.getRightChild());
                }
            }
        }
    }
    
    public static void main(String[] args){
        //Create several leaf elements.  
        //At first, they are actually 8 different binary trees of one element each.
        HW4_SampleSolution t = new HW4_SampleSolution("A");
        HW4_SampleSolution t1 = new HW4_SampleSolution("B");
        HW4_SampleSolution t2 = new HW4_SampleSolution("C");
        HW4_SampleSolution t3 = new HW4_SampleSolution("D");
        HW4_SampleSolution t4 = new HW4_SampleSolution("E");
        HW4_SampleSolution t5 = new HW4_SampleSolution("F");
        HW4_SampleSolution t6 = new HW4_SampleSolution("G");
        HW4_SampleSolution t7 = new HW4_SampleSolution("H");
        
        //Link the elements together to form one large binary tree.
        t3.setLeftChild(t7);
        t1.setLeftChild(t3);
        t1.setRightChild(t4);
        t2.setLeftChild(t5);
        t2.setRightChild(t6);
        t.setLeftChild(t1);
        t.setRightChild(t2);
        
        //Print out the traversals of the tree.
        System.out.println("inOrder: ");
        inOrder(t);
        System.out.println("\npostOrder: ");
        postOrder(t);
        System.out.println("\npreOrder: ");
        preOrder(t);
        System.out.println("\nbreadthFirst: ");
        breadthFirst(t);
        System.out.println();
    }
}
