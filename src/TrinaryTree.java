/**
 * Trinary Tree class with nodes having 3 children. Two methods - insert and delete are implemented.
 * A tri-nary tree is much like a binary tree but with three child nodes for each parent instead of two -- with the left node being values
less than the parent, the right node values greater than the parent, and the middle nodes values
equal to the parent.
 * @author Jigar Patel
 *
 */
public class TrinaryTree {

	/**
	 * Used as node of the tree	  
	 */
	public class Node{
		int value;
		Node leftChild,rightChild,midChild;
		/**
		 * Initialize a node with no children
		 * @param value represents value of the node
		 */
		public Node(int value){
			this.value = value;
			this.leftChild = this.rightChild = this.midChild = null; 
		}
	}
	
	Node root;
	Node parentNode = null; //Used as temp node
	String childType = ""; // Used to indicate the type of child - left or right

	public TrinaryTree(){
		//Create an empty root node
		root = null;
	}
	/**
	 * Inserts a new node in the tree
	 * @param value the value of the new node
	 * @return New Node with appropriate children mapped
	 */
	public Node insert(int value){
		Node newNode = new Node(value);
		//Handle two trivial cases
		Node currentNode = root;
		if(currentNode == null){
			//Inserting first node
			root = newNode;
		}
		else{
			if(newNode.value == currentNode.value){
				//Insert the new node as mid child of the current node
				newNode.midChild = currentNode.midChild;
				currentNode.midChild = newNode;
			}
			else
			{//find the appropriate place of node using recursion
				insertNode(currentNode, newNode);
			}
		}
		return newNode;
	}
	/**
	 * Private method which finds the appropriate location to insert and inserts new node using recursion.
	 * It will return the moment it finds that node under examination is null
	 * @param currentNode the current node being examined
	 * @param newNode the new node to insert
	 */
	private void insertNode(Node currentNode, Node newNode){
		if(newNode.value < currentNode.value){
			if(currentNode.leftChild != null)
				insertNode(currentNode.leftChild,newNode);
			else
				currentNode.leftChild = newNode;
		}
		else if(newNode.value > currentNode.value){
			if(currentNode.rightChild != null)
				insertNode(currentNode.rightChild,newNode);
			else
				currentNode.rightChild = newNode;
		}
		else{
			newNode.midChild = currentNode.midChild;
			currentNode.midChild = newNode;
		}
	}
	/**
	 * Delete the node with value, if any, else throws exception
	 * @param value the value to delete from tree
	 */
	public void delete(int value){
		//Find the node to delete (along with its parent)
		Node currentNode = root;
		parentNode = null; //represents parent node of node to delete

		Node nodeToDelete = findNodeToDelete(value, currentNode);		

		//Find successor of the node to delete
		Node successorNode = findSuccessorAndResetChildren(nodeToDelete);

		//Set the successorNode as appropriate child of parentNode
		if(parentNode != null){
			if(childType == "left")
				parentNode.leftChild = successorNode;
			else if(childType == "right")
				parentNode.rightChild = successorNode;			
		}
		else{
			//Node to delete is root child
			this.root = successorNode;
		}
	}
	/**
	 * Finds the successor node of the node to delete and also resets the children of successor node appropriately
	 * @param nodeToDelete node which is going to be replaced
	 * @return successor node which will replace the node to delete
	 */
	private Node findSuccessorAndResetChildren(Node nodeToDelete) {
		Node successorNode = null;
		//if node is leaf then there is no successor
		if(nodeToDelete.leftChild == null && nodeToDelete.midChild == null && nodeToDelete.rightChild == null){
			return null;
		}			
		//if node has middle child then that becomes successor
		else if(nodeToDelete.midChild != null){
			successorNode = nodeToDelete.midChild;
			//Reset the left and right children of successor
			successorNode.leftChild = nodeToDelete.leftChild;
			successorNode.rightChild = nodeToDelete.rightChild;
			return successorNode;
		}
		//if node has left child but no right child then left becomes successor
		else if(nodeToDelete.rightChild == null && nodeToDelete.leftChild != null){
			//As the node does not have mid or right children no need to reset any children
			successorNode = nodeToDelete.leftChild; 
			return successorNode;
		}
		else {
			//Node has right child => find the minimum of the right subtree
			successorNode = findMinNodeAndResetParent(nodeToDelete.rightChild,nodeToDelete.rightChild);
			successorNode.leftChild = nodeToDelete.leftChild;
			if(nodeToDelete.rightChild != successorNode)
				successorNode.rightChild = nodeToDelete.rightChild;
			return successorNode;
		}
	}
	/**
	 * 	Finds the minimum node by going left until we find null
	 *  Resets the parent node's left child such that minNode's right child is not lost
	 *  Min node's mid child will be carried forward with Min node
	 *  Min node will not have left child so no need to take care of
	 * @param n starting node (root of sub tree)
	 * @param parentNode parentNode of the current node 
	 * @return the node with minimum value in the subtree starting with n
	 */
	private Node findMinNodeAndResetParent(Node n,Node parentNode){
		if(n.leftChild == null){
			if(n.rightChild != null)
				parentNode.leftChild = n.rightChild;
			else
				parentNode.leftChild = null;
			return n;
		}
		else{
			//keep going on in left
			return findMinNodeAndResetParent(n.leftChild,n);
		}
	}
	/**
	 * Find the node to delete and 
	 * 	also sets 2 class variables - parentNode to the parent of nodeToDelete 
	 * 		and childType to indicate whether node to delete is left or right child
	 * Throws exception if node not found
	 * @param value value to delete
	 * @param currentNode starting node
	 * @return node to delete
	 */
	private Node findNodeToDelete(int value, Node currentNode) {
		if(currentNode == null){
			//Node to delete does not exist
			throw new RuntimeException();
		}

		else if(currentNode.value == value){
			return currentNode;
		}
		else if(currentNode.value > value){
			//go left			
			parentNode = currentNode;
			childType = "left";
			return findNodeToDelete(value,currentNode.leftChild);
		}
		else{
			//go right
			parentNode = currentNode;
			childType = "right";
			return findNodeToDelete(value,currentNode.rightChild);
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrinaryTree t = new TrinaryTree();
		int[] input = new int[]{5,4,9,5,7,2,2};
		for(int i=0; i<input.length; i++){
			t.insert(input[i]);
		}
		//Delete number not existing
		//t.delete(8);
		//Delete root
		t.delete(5);
		//t.delete(5);
		//Delete leaf
		t.delete(7);
		//Delete middle
		t.delete(2);		
		//t.delete(9);
		if(t.root != null){
			printTree(t.root);
		}
	}
	private static void printTree(Node n){		
		System.out.println(n.value);
		if(n.leftChild != null)
			printTree(n.leftChild);
		if(n.midChild != null)
			printTree(n.midChild);
		if(n.rightChild != null)
			printTree(n.rightChild);
	}
}



