import java.util.*;
//This uses Back Tracking 
public class SnakeSequence{
	static int max;
	static List<List<Integer>> allSequences;
	public static void main(String[] args){
		int[][] input = {
			{1,5,6,5},
			{2,4,3,2},
			{3,2,1,1}
		};

		printMaxSequences(input);
	}

	public static void printMaxSequences(int[][] input){
		if(input.length > 0){
			allSequences = new ArrayList<List<Integer>>();
			max = 0;
			int rows = input.length;
			int cols = input[0].length;
			//Used to ensure that if a cell is already part of some sequence
			//then we need not test any sequences starting from that cell as it would be smaller than earlier.
			boolean[][] visited = new boolean[rows][cols];
			//Check for each cell what max sequence we can get
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					if(!visited[i][j]){
						List<Integer> newSequence = new ArrayList<Integer>();						
						getNewSequence(input, visited, i, j, newSequence);											

					}
				}
			}			
			for(List<Integer> sequence : allSequences){
				if(sequence.size() == max)
					System.out.println(sequence);
			}
		}
	}
	private static void getNewSequence(int[][] input, boolean[][] visited, int row, int col, List<Integer> currSequence){
		//Add the current element in sequence
		int currElement = input[row][col];
		currSequence.add(currElement);
		visited[row][col] = true;
		//Check if we can go right or bottom and if yes, update currSequence
		if((col < (input[0].length-1)) && (Math.abs(currElement - input[row][col+1]) == 1)){
			getNewSequence(input,visited,row,col+1,currSequence);
		}
		if((row < (input.length-1)) && (Math.abs(currElement - input[row+1][col]) == 1) ){
			getNewSequence(input,visited,row+1, col, currSequence);
		}		
		if(max < currSequence.size())
			max = currSequence.size();

		allSequences.add(getNewCopy(currSequence)); //Note that we are doing deep copy
		//remove the curr element from sequence
		currSequence.remove(currSequence.size()-1);
	}
	private static List<Integer> getNewCopy(List<Integer> list){
		List<Integer> newList = new ArrayList<Integer>();
		for(Integer i : list)
			newList.add(i);
		return newList;
	}

}