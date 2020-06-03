import java.util.LinkedList;
import java.util.Queue;

//A simple binary tree class.  
//Does not do anything fancy other than imitate the abstract binary tree data type.
public class HW4_SimpleBinaryTree {
	public Object value;						//The value of an element.
	private HW4_SimpleBinaryTree leftChild;		//The element's left child.
	private HW4_SimpleBinaryTree rightChild;	//The element's right child.
	
	//Create an element and assign it a value and two children.
	public HW4_SimpleBinaryTree(Object value, HW4_SimpleBinaryTree leftChild, HW4_SimpleBinaryTree rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	//Leaf constructor.
	//Create an element and assign it no children.
	public HW4_SimpleBinaryTree(Object value) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public Object getValue() {return value;}
	public HW4_SimpleBinaryTree getLeftChild() {return leftChild;}
	public HW4_SimpleBinaryTree getRightChild() {return rightChild;}
	
	public void setLeftChild(HW4_SimpleBinaryTree subtree) {this.leftChild=subtree;}
	public void setRightChild(HW4_SimpleBinaryTree subtree) {this.rightChild=subtree;}
	public void setValue(Object value) {this.value = value;}
	
	//If an element's children are null, it is a leaf.
	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}
	
	public static void inOrder(HW4_SimpleBinaryTree t){
		//IMPLEMENT ME
		// left -> root -> right
		if (t != null) {
			inOrder(t.getLeftChild());
			System.out.print(t.getValue() + " ");
			inOrder(t.getRightChild());
		}
	}
	
	public static void postOrder(HW4_SimpleBinaryTree t){
		//IMPLEMENT ME
		// left -> right -> root
		if (t != null) {
			postOrder(t.getLeftChild());
			postOrder(t.getRightChild());
			System.out.print(t.getValue() + " ");
		}
	}
	
	public static void preOrder(HW4_SimpleBinaryTree t){
		//IMPLEMENT ME
		// root -> left -> right
		if (t != null) {
			System.out.print(t.getValue() + " ");
			preOrder(t.getLeftChild());
			preOrder(t.getRightChild());
		}
	}
	public static void breadthFirst(HW4_SimpleBinaryTree t){
		//IMPLEMENT ME
		// root -> left (level 1) -> right (level 1) -> left (level 2) -> right (level 2) ->...
		if (t != null) {
			Queue<Object> q = new LinkedList<> ();
			q.add(t);
			
			while (!q.isEmpty()) {
				HW4_SimpleBinaryTree tree = (HW4_SimpleBinaryTree) q.remove();
				System.out.print(tree.getValue() + " ");
				if (tree.getLeftChild() != null) {q.add(tree.getLeftChild());}
				if (tree.getRightChild() != null) {q.add(tree.getRightChild());}
			}
		}
	}
	
	public static void main(String[] args){
		//Create several leaf elements.
		//At first, they are actually 8 different binary trees of one element each.
		HW4_SimpleBinaryTree t = new HW4_SimpleBinaryTree("A");
		HW4_SimpleBinaryTree t1 = new HW4_SimpleBinaryTree("B");
		HW4_SimpleBinaryTree t2 = new HW4_SimpleBinaryTree("C");
		HW4_SimpleBinaryTree t3 = new HW4_SimpleBinaryTree("D");
		HW4_SimpleBinaryTree t4 = new HW4_SimpleBinaryTree("E");
		HW4_SimpleBinaryTree t5 = new HW4_SimpleBinaryTree("F");
		HW4_SimpleBinaryTree t6 = new HW4_SimpleBinaryTree("G");
		HW4_SimpleBinaryTree t7 = new HW4_SimpleBinaryTree("H");
		
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
