package pblms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Maze {
	static HashSet<Node> visited = new HashSet<Node>();
	static boolean[][] mat = new boolean[4][4];
	private static class Node{
		Integer x;
		Integer y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode(){
			return this.x.hashCode() ^ this.y.hashCode();
		}
		@Override
		public boolean equals(Object n){
			if(n == null)
				return false;
			if(this == n)
				return true;
			Node other = (Node)n;
			return this.x == other.x && this.y == other.y;
		}
		public String toString(){
			return this.x + " " + this.y;
		}
	}
	public static void main(String[] args){
		Node start = new Node(0,0);
		Node end = new Node(3,3);
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++)
				mat[i][j] = true;
		}
		mat[0][2] = false;
		mat[3][1] = false;
		mat[3][1] = false;
		mat[2][3] = false;
		List<Node> path = new ArrayList<Node>();
		if(getPath(start,end, path)){
			System.out.println(path);
		}
	}
	public static boolean getPath(Node s, Node e, List<Node> path){
		path.add(s);
		visited.add(s);
		if(s.equals(e)){			
			return true;
		}
		Node right = new Node(s.x+1,s.y);
		if(isvalid(right)){
			if(getPath(right,e,path))
				return true;			
		}
		
		Node bottom = new Node(s.x,s.y+1);
		if(isvalid(bottom)){
			if(getPath(bottom,e,path))
				return true;			
		}
		Node left = new Node(s.x-1,s.y); 
		if(isvalid(left)){
			if(getPath(left,e,path))
				return true;			
		}
		Node top = new Node(s.x,s.y-1);
		if(isvalid(top)){
			if(getPath(top,e,path))
				return true;			
		}
		path.remove(s);
		return false;
	}
	private static boolean isvalid(Node n) {
		if(n.x >=0 && n.x < mat[0].length && n.y >=0 && n.y < mat.length && !visited.contains(n) && mat[n.x][n.y]){
			return true;
		}
		return false;
	}
}
