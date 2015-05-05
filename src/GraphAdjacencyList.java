package edu.cmu.jjpatel;

import java.util.*;
//Directed Graph
public class GraphAdjacencyList{

	private LinkedList<Integer>[] internalArray;
	private int vertices;
	private int edges;

	public GraphAdjacencyList(int numberOfVertices){
		this.vertices = numberOfVertices;
		internalArray = (LinkedList<Integer>[]) new LinkedList[vertices];
		edges = 0;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<internalArray.length; i++){
			LinkedList<Integer> l = internalArray[i];
			if(l != null){
				for(Integer j : l)
					sb.append("Edge " + i + "-" + j + "\n");
			}
		}
		return sb.toString();
	}
	public void addEdge(Integer node1, Integer node2){
		if(node1 >= vertices || node2 >= vertices || node1 < 0 || node2 < 0)
			throw new IndexOutOfBoundsException("No such vertex");
		if(internalArray[node1] == null)
			internalArray[node1] = new LinkedList<Integer>();

		for(Integer i : internalArray[node1]){
			if(i == node2)
				return; //edge already exists
		}
		internalArray[node1].add(node2);
		edges++;
	}
	public void removeEdge(Integer node1, Integer node2){
		if(node1 >= vertices || node2 >= vertices || node1 < 0 || node2 < 0)
			throw new IndexOutOfBoundsException("No such vertex");
		LinkedList<Integer> l = internalArray[node1];
		if(l != null){
			if(l.remove(node2))
				edges--;
		}

	}
	public static void main(String args[]){
		GraphAdjacencyList g = new GraphAdjacencyList(3);
		g.addEdge(1,2);
		g.addEdge(0,1);
		System.out.println(g);
		g.removeEdge(0,1);
		System.out.println(g);
	}
}
