package edu.cmu.jjpatel;

public class GraphAdjacencyMatrix{

	private int vertices;
	private int edges;
	private boolean[][] matrix;
	private boolean isDirected;

	public GraphAdjacencyMatrix(int numberOfVertices,boolean isDirected){
		this.vertices  = numberOfVertices;
		this.isDirected = isDirected;
		matrix = new boolean[vertices][vertices];
		for(int i=0; i<vertices; i++)
			matrix[i][i] = true;
	}
	public void addEdge(int node1, int node2){
		if(node1 >= vertices || node2 >= vertices)
			throw new IndexOutOfBoundsException("No such vertex");
		else{
			if(!matrix[node1][node2]){
				matrix[node1][node2] = true;
				edges++;
			}
			if(!isDirected)
				matrix[node2][node1] = true;
		}
	}
	public void removeEdge(int node1, int node2){
		if(node1 >= vertices || node2 >= vertices)
			throw new IndexOutOfBoundsException("No such vertex");
		else{
			if(matrix[node1][node2]){
				matrix[node1][node2] = false;
				edges--;
			}
			if(!isDirected)
				matrix[node2][node1] = false;
		}
		
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Number of vertices: " + vertices + "\n");
		for(int i=0; i<vertices; i++){
			for(int j=0; j<vertices; j++){
				if(matrix[i][j] && i != j)
					sb.append("Edge " + i + "-" + j + "\n");
			}
		}
		return sb.toString();
	}
	public static void main(String args[]){
		GraphAdjacencyMatrix g = new GraphAdjacencyMatrix(3,true);
		g.addEdge(1,2);
		g.addEdge(0,1);
		System.out.println(g);
		g.removeEdge(1,2);
		System.out.println(g);
	}
}
