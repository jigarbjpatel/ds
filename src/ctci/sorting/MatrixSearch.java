public class MatrixSearch{

	public static void main(String args[]){
		int N = 3;
		int[][] matrix = new int[N][N];
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				matrix[i][j] = j+i;
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		MatrixSearch ms = new MatrixSearch();
		System.out.println(ms.search(4,matrix));	
	}
	public boolean search(int elem, int[][] matrix){
		//start from the end of first row 
		int N = matrix[0].length;
		int row = 0;
		int col = N-1;	
		while(row < N && col >=0){
			if(matrix[row][col] == elem)
				return true;
			else if(matrix[row][col] > elem) // means elem cannot be in this column
				col--;
			else
				row++; //elem is bigger => it cannot be in this row 
		}
		return false;
	}
}
