public class MatrixSetZeros{
	public static void main(String args[]){
		int[][] matrix = new int[3][2];
		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix[0].length; j++)
				matrix[i][j] = i+j;
		
		boolean[] rows = new boolean[matrix.length];
		boolean[] cols = new boolean[matrix[0].length];
		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix[0].length; j++){
				if(matrix[i][j] == 0){
					rows[i] = true; 
					cols[j] = true;
				}
			}
		for(int i=0; i<rows.length; i++)
			if(rows[i] == true)	
				for(int j=0; j<cols.length; j++)
					matrix[i][j] = 0;
		for(int i=0; i<cols.length; i++)
			if(cols[i] == true)	
				for(int j=0; j<rows.length; j++)
					matrix[j][i] = 0;
		for(int i=0; i<rows.length; i++){
			for(int j=0; j<cols.length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
		
	}
}
