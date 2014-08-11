package common;
import java.util.*;
/**
	Time Complexity - O(v + e)
	Nodes are not integers
*/
public class BFS{
	public static void main(String args[]){
		Graph g = new Graph();
		for(int i=0; i<10; i++){
			Node n = new Node(i,String.valueOf(i));
			g.addNode(n);
		}
		for(Node n: g.nodes){
			for(Node j: g.nodes){
				int x = (int)(10 * Math.random());
				if(x % 5 == 0){
					n.addAdjacent(j);	
					j.addAdjacent(n);
				}
			}
		}
		for(Node n: g.nodes){
			System.out.println(n);	
		}

		Node src = g.nodes.get(0);
		Node dest = g.nodes.get(1);

		printPath(src,dest);
		
	}
	protected static void printPath(Node src, Node dest){
		LinkedList<Node> q = new LinkedList<Node>();
		//Key is node.id and value is previous node's id
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		q.add(src);
		map.put(src.id,src.id);
		Node curr = null;
		
		while(!q.isEmpty()){
			curr = q.pollFirst();
			if(curr.value.equals(dest.value)){
				break;
			}
			for(Node n : curr.getAdjacent()){
				if(map.get(n.id) == null){
					q.offerLast(n);
					map.put(n.id,curr.id);			
				}
			}
		}
/*		for(Map.Entry e : map.entrySet()){
			System.out.println(e.getKey() + " " +e.getValue());
		}*/
		Integer currentId = curr.id;
		while(true){
			System.out.println(currentId);
			currentId = map.get(currentId);
			if(currentId == src.id)
				break;
		}
		System.out.println(src.id);
	}
	private static class Graph{
		public List<Node> nodes = new ArrayList<Node>();
		
		public void addNode(Node n){
			nodes.add(n);
		}
		public List<Node> getAdjacentNodes(Node n){
			return n.adjacent;		
		}
	}
	private static class Node{
		Integer id;
		String value;
		List<Node> adjacent;
		public Node(Integer id, String value){
			this.id = id;
			this.value = value;
			adjacent = new LinkedList<Node>();
		}
		public List<Node> getAdjacent(){
			return adjacent;
		}
		public void addAdjacent(Node n){
			//Assuming that duplicates are not entered by user
			if(!this.equals(n))
				adjacent.add(n);
		}
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(this.value + ":");
			for(Node n : this.adjacent){
				sb.append(n.value + ",");
			}
			return sb.toString();
		}
	}
}
