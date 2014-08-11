public class NQueens{
	public static void main(String args[]){
		int N = 8;
		NQueens q = new NQueens();
		//sol array contains the columns in which the queens will be placed
		int[] sol = new int[N];
		for(int i=0; i<N; i++)
			sol[i] = -1;
		if(q.solve(N,sol)){
			for(int i=0;i<N;i++)
				System.out.println(sol[i]);
		}
	}
	public boolean solve(int N, int[] sol){
		return solve(0,N,sol);
	}
	private boolean solve(int currRow, int N, int[] sol){
		if(currRow == N)
			return true;
		for(int col=0; col<N; col++){
			if(isValid(currRow,col,sol)){
				sol[currRow] = col;
				if(solve(currRow + 1,N,sol))
					return true;
				sol[currRow] = -1;
			}
		}
		return false;
	}
	private boolean isValid(int currRow, int col, int[] sol){
		for(int i=0; i<currRow; i++){
			//check for same column
			if(sol[i] == col)
				return false;
			//check for same diagnol (both on left and right)
			int diffInRows = currRow - i;
			if(Math.abs(sol[i] - col) == diffInRows)
				return false;
		}
		return true;
	}

}
