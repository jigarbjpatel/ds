package pblms;

import java.util.HashMap;
import java.util.LinkedList;

public class PowerOutage {

	public static void main(String[] args) {
		PowerOutage p = new PowerOutage();
		int[] from = {0,0,0,1,4};
		int[] to = {1,3,4,2,5};
		int[] cost = {10,10,100,10,5};
		System.out.println("Min Time: " + p.estimateTimeOut(from, to, cost));
	}
	
	public int estimateTimeOut(int[] fromJunction, int[] toJunction, int[] ductLength){
		int minTime = 0;
		//Build a tree with each node having cost associated with it which is the total cost of traversing all its kids
		//Find the cost of traversing the subtree rooted at each children of root
		//Then except for the most costly child, double the cost for rest of the children
		HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();
		Node n = null;
		for(int i=0; i<fromJunction.length; i++){
			if(nodes.containsKey(fromJunction[i])){
				n = nodes.get(fromJunction[i]);
			}else{
				n = new Node(fromJunction[i],0);
				n.children = new LinkedList<Node>();
				nodes.put(n.id,n);
			}
			n.children.add(new Node(toJunction[i], ductLength[i]));
		}
		Node root = nodes.get(0);
		//System.out.println(nodes);
		buildTree(root, nodes);
		minTime = calculateCosts(root, true);
		
		printTree(root);
		return minTime;
	}
	private int calculateCosts(Node root, boolean isRoot) {
		int totalCost = 0;
		int[] costs;
		Node n;
		int max =0;
		int maxId = 0;
		if(root != null){
			costs = new int[root.children.size()];
			for(int i=0; i<root.children.size(); i++){
				n = root.children.get(i);
				if(isRoot){
					costs[i] = (int) (n.cost + calculateCosts(n,false));
					if(costs[i] > max){
						max = costs[i];
						maxId = i;
					}
				}else{
					totalCost += n.cost + calculateCosts(n,false);
				}
			}
			if(isRoot){
				for(int i=0; i<costs.length; i++){
					if(i != maxId)
						totalCost += 2*costs[i];
					else
						totalCost += root.children.get(i).cost + calculateCosts(root.children.get(i), true);
				}
			}
		}
		return totalCost;
	}

	private void printTree(Node root) {
		if(root != null){
			System.out.println(root.toString());
			if(root.children != null){
				for(Node n: root.children)
					printTree(n);
			}
		}
	}

	private void buildTree(Node root, HashMap<Integer, Node> nodes) {
		if(root != null){
			Node n;
			for(Node child : root.children){
				n = nodes.get(child.id);
				if(n != null){
					child.children = n.children;
					buildTree(child, nodes);
				}
			}
		}
	}
	private class Node{
		public int id;
		public long cost;
		public LinkedList<Node> children;
		public Node(int id, int cost){
			this.id = id;
			this.cost = cost;
			children = new LinkedList<Node>();
		}
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for(Node n : this.children){
				sb.append("id " + n.id);
				sb.append(" ");
			}
			return "id: "+ id + " cost: "+ cost + " children: " + sb.toString() + "\n";
			
		}
	}

}
