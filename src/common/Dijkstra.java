package common;
import java.util.*;
/**
 * Works only for non-negative weights
 */
public class Dijkstra{
	public static void main(String args[]){
		//Given a graph with n vertices and e edges find shortest path from a given vertex v
		int V = 4;
		Graph g = new Graph(V);
		g.addEdge(0,1,10);
		g.addEdge(1,2,5);
		g.addEdge(0,2,20);
		g.addEdge(2,0,1);
		g.addEdge(1,0,100);
		g.addEdge(0,3,10);
		g.addEdge(3,1,50);
		g.addEdge(2,3,2);
		//intialize distance and path array
		int source = 0;
		int[] dist = new int[V];
		for(int i=0;i<V;i++)
			dist[i] = Integer.MAX_VALUE;
		dist[source] = 0;
		int[] path = new int[V];
		for(int i=0; i<V; i++)
			path[i] = -1;
		//intialize min heap of nodes (here nodes are interpreted as composed of vertex and its min distance from the source)
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(source,dist[source]));
		boolean[] visited = new boolean[V]; //Used to ensure same node is not put on queue again.

		while(!q.isEmpty()){
			//Take out the node with minimum distance from source (which will be at top of queue)
			Node w = q.poll();
			//If only path to one node is required stop here if w is the target node
			visited[w.vertex] = true;
			//for all the adjacent nodes of exit node check if we can reach to adjacent node from src in a cheaper way	
			//n is the target node here and w is intermediate or exit node
			for(Node n : g.getAdjacentVertices(w.vertex)){
				int distThroughW = dist[w.vertex] + n.weight; //n.weight is the cost of going to n from w
				if(dist[n.vertex] > distThroughW){ 
					//Update/Insert the node with new weight in the queue only if node is not visited earlier
					if(!visited[n.vertex]){
						boolean found = false;
						Node temp = new Node(n.vertex, dist[n.vertex]);
						for(Node t : q){
							if(t.equals(temp)){
								t.weight = distThroughW;
								found = true;
							}
						}
						if(!found){
							temp.weight = distThroughW;
							q.add(temp);
						}
					}
					dist[n.vertex] = distThroughW;	
					path[n.vertex] = w.vertex;
				}
			}
		}

		for(int i=0; i<V; i++)
			System.out.println(dist[i]);

		List<Integer> p = new ArrayList<Integer>();		
		for(int i=0; i<V; i++){
			System.out.println("Path from " + source +" to " + i);
			int previousVertex = path[i];
			if(previousVertex != -1)
				p.add(i);
			while(previousVertex != -1){
				p.add(previousVertex);
				previousVertex = path[previousVertex];
			}
			Collections.reverse(p);
			System.out.println(p);
			p.clear();
		}
	}
}

	class Node implements Comparable<Node>{
			Integer vertex;
			Integer weight;
			public Node(int vertex, int weight){
				this.vertex = vertex;
				this.weight = weight;
			}			
			public String toString(){
				return this.vertex + " " + this.weight;
			}
			public int compareTo(Node other){
				return Integer.compare(this.weight, other.weight);
			}
			public boolean equals(Node other){
				if(other == null)
					return false;
				return this.vertex.equals(other.vertex) && this.weight.equals(other.weight);
			}
			public int hashCode(){
				return vertex.hashCode() ^ weight.hashCode();
			}
	}

	class Graph{
		private int vertices;
		private LinkedList<Node>[] edges;

		public Graph(int V){
			this.vertices = V;
			this.edges = (LinkedList<Node>[]) new LinkedList[V];
			for(int i=0; i<V; i++)
				edges[i] = new LinkedList<Node>();
		}
		public void addEdge(int source, int dest, int weight){
			if(source < vertices && dest < vertices){
				boolean found = false;
				for(Node n : edges[source]){
					if(n.vertex == dest){
						n.weight = weight;
						found = true;
						break;
					}
				}
				if(!found){
					Node n = new Node(dest,weight);
					edges[source].add(n);
				}
			}
		}
		public LinkedList<Node> getAdjacentVertices(int V){
			return edges[V];
		}
	}

