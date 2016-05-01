package common;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinarySearchTree {

	private class Node{
		int value;
		Node left;
		Node right;
		public Node(int value){
			left = right = null;
			this.value = value;
		}
	}
	private Node root;
	public BinarySearchTree(){
		root = null;
	}
	public void insert(int value){
		root =	insert(root,value);
	}	
	public Node insert(Node n, int value){
		if(n==null){
			return new Node(value);
		}else if(n.value < value){
			n.right = insert(n.right,value);
		}else if(n.value > value){
			n.left = insert(n.left,value);
		}//No equal values are allowed
		return n;
	}
	public Node search(int value){
		return search(root,value);		
	}
	public Node search(Node n, int value){
		if(n==null)
			return null;
		else if(n.value == value)
			return n;
		else if(n.value < value)
			return search(n.right,value);
		else
			return search(n.left,value);
	}
	public void print(){
		print(root);
	}
	public void print(Node n){
		if(n==null)
			return;
		print(n.left);
		System.out.println(n.value);
		print(n.right);
	}
	/*public void deleteMin(){
		deleteMin(root);
	}
	public void deleteMin(Node n){
		if(n != null){
			Node parent = n;
			while(n.left != null){
				parent = n;
				n = n.left;
			}
			parent.left = n.right;
		}
	}*/
	public void deleteMin(){
		//check if root is not null
		root = deleteMin(root);
	}
	//deletemin will return either right of the input node if input node is being deleted or
	//it will return the modified subtree rooted at inputnode.left
	public Node deleteMin(Node n){
		if(n == null)
			return null;
		if(n.left == null)
			return n.right;
		n.left = deleteMin(n.left);
		return n;
	}
	public void deleteMax(){
		root = deleteMax(root);
	}
	public Node deleteMax(Node n){
		if(n == null)
			return null;
		if(n.right == null)
			return n.left;
		n.right  = deleteMax(n.right);
		return n;
	}

	public void delete(int value){
		root = delete(root,value);
	}
	public Node delete(Node n, int value){
		if(n == null)
			return null;
		if(n.value < value)
			n.right = delete(n.right,value); // THis will take care of reseting the link between parent and deleted child node
		else if(n.value > value)
			n.left = delete(n.left,value);
		else{
			if(n.left == null && n.right == null)
				return null; //Node is a leaf node
			else if(n.left == null)
				return n.right;
			else if(n.right == null)
				return n.left;
			else{
				//first get the successor node
				Node temp = n;
				n = getMin(temp.right);

				n.right = deleteMin(temp.right);
				n.left = temp.left;

			}
		}
		return n;

	}
	public Node getMinUsingWhile(Node n){
		if(n == null)
			return null;
		while(n.left != null)
			n = n.left;
		return n;
	}
	public Node getMin(){
		if(root == null)
			return null;
		return getMin(root);
	}
	public Node getMin(Node n){
		if(n.left == null)
			return n;
		else
			return getMin(n.left);
	}
	public Node getMax(){
		if(root == null)
			return null;
		else
			return getMax(root);
	}
	public Node getMax(Node n){
		if(n.right == null)
			return n;
		else
			return getMax(n.right);
	}
	public int size(){
		return size(root);
	}
	public int size(Node n){
		if(n==null)
			return 0;
		return 1 + size(n.left) + size(n.right);
	}
	public int maxDepth(Node n){
		if(n==null)
			return 0;
		else {
			int leftMax = maxDepth(n.left);
			int rightMax = maxDepth(n.right);
			return 1 + Math.max(leftMax,rightMax);
		}
	}
	public void printPostOrder(){
		printPostOrder(root);
	}
	public void printPostOrder(Node n){
		if(n == null)
			return;
		printPostOrder(n.left);
		printPostOrder(n.right);
		System.out.println(n.value);
	}
	/**
	 * Checks to see if there is any path from root to leaf where node and its children's value is equal to the sum
	 * @param n - starting node
	 * @param sum - the sum to check
	 * @return true or false
	 */
	boolean hasPathSum(Node n, int sum){
		if(n == null)
			return false;
		sum = sum - n.value;
		if(sum == 0){
			//return true if this is leaf node else false
			return (n.left == null && n.right == null);			
		}else{
			if(hasPathSum(n.left,sum))
				return true;
			else if(hasPathSum(n.right,sum))
				return true;
			else
				return false;
		}
	}
	/**
	 * Print all root-to-leaf paths
	 */
	public void printAllPaths(){
		if(root != null){
			int[] currentPath = new int[maxDepth(root)+1];
			int pathLen = 0;
			printAllPaths(root,currentPath,pathLen);
		}
	}
	/**
	 * Prints all possible root-to-leaf paths
	 * @param n
	 * @param currentPath - array used to pass path created until this node
	 * @param pathLen - length of path until now
	 */
	private void printAllPaths(Node n, int[] currentPath, int pathLen) {
		//add the currnt node to path
		if(n==null)
			return;
		currentPath[pathLen] = n.value;
		pathLen++;
		if(n.left == null && n.right == null){
			//print current path
			for(int i=0;i<pathLen;i++)
				System.out.println(currentPath[i]);
			System.out.println("New Path");
			return;
		}else{
			if(n.left!=null)
				printAllPaths(n.left,currentPath,pathLen);
			if(n.right!=null)
				printAllPaths(n.right,currentPath,pathLen);
		}
	}
	public void mirror(Node n){
		if(n==null)
			return;
		//swap the children
		Node temp = n.left;
		n.left = n.right;
		n.right = temp;
		mirror(n.left);
		mirror(n.right);
	}
	/**
	 * Counts the number of trees possible with values from 1 to n
	 * @param n
	 * @return
	 */
	public int countTrees(int n){
		if(n<1)
			return 0;
		if(n==1)
			return 1;
		int totalCount =0;
		int leftTotal=0, rightTotal=0;
		for(int i=1; i<=n; i++){
			//consider each element as root
			leftTotal = (i-1 > 0) ? countTrees(i-1) : 1;
			rightTotal =(n-i) > 0 ? countTrees(n-i) : 1; 
			totalCount += leftTotal * rightTotal;
		}
		return totalCount;
	}
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(4);
		bst.insert(2);
		bst.insert(1);
		bst.insert(3);
		bst.insert(8);
		bst.insert(6);
		bst.insert(10);
		//System.out.println(bst.countTrees(4));
		//bst.mirror(bst.root);

		//bst.printAllPaths();
		/*System.out.println(bst.getMax().value);
		System.out.println(bst.hasPathSum(bst.root, 7));
		bst.deleteMin();
		bst.delete(4);
		bst.print();*/

		/*LinkedList<String> test = new LinkedList<String>();
		test.add("test");
		test.remove();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		int i = q.remove();
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		int j = s.pop();
		s.peek();*/
		/*bst.printPostOrder();
		System.out.println(bst.getMinUsingWhile(bst.root).value);
		System.out.println(bst.getMax().value);
		System.out.println(bst.size());
		System.out.println(bst.maxDepth(bst.root));*/

		//System.out.println(bst.search(4).value);
System.out.println(bst.isBST());
	}
	public boolean isBST(){
		return isBST(root);
	}
	private boolean isBST(Node n) {
		if(n==null)
			return true;
		
		if((n.right == null || (n.right != null && getMin(n.right).value > n.value))
				&& (n.left == null || (n.left != null && getMax(n.left).value < n.value))){
			return isBST(n.left) && isBST(n.right);
		}		
		return false;
	}
}
