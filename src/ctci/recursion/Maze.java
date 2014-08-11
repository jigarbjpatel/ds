import java.util.*;

public class Maze{
	static class Cell{
		int x;
		int y;
		boolean allowed;
		public Cell(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Cell(int x,int y, boolean allowed){
			this.x = x;
			this.y = y;
			this.allowed = allowed;
		}
	}
	static int N = 3;
	public static void main(String args[]){
		//Find a path in maze using only right and down steps
		Cell[][] maze = new Cell[N][N];
		ArrayList<Cell> path = new ArrayList<Cell>();
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				maze[i][j] = new Cell(i,j,(i==0 || j==N-1) ? true : false);

		if(solveMaze(maze,maze[0][0],path)){
			for(Cell c: path)
				System.out.println(c.x + " " + c.y + " ");
		}
	}

	private static boolean solveMaze(Cell[][] maze, Cell c,  ArrayList<Cell> path){
		path.add(c);
		if(c.x == N-1 && c.y == N-1)
			return true;
		if(c.x < N && c.y+1 < N && maze[c.x][c.y+1].allowed){
			//Try right if allowed
			if(solveMaze(maze, maze[c.x][c.y+1], path)) 
				return true;
		}
		if(c.x+1 < N && c.y < N && maze[c.x+1][c.y].allowed){
			//Try down
			if(solveMaze(maze, new Cell(c.x+1, c.y), path))
				return true;
		}
		path.remove(c); //backtrack
		return false;
	}
}
