import java.util.*;
//This uses Dynamic Programming approach
public class SnakeSequenceDP{
	static int max;	
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
			
			max = 0;
			int rows = input.length;
			int cols = input[0].length;
			//Each cell will indicate the max sequence ending at that point
			int[][] maxSequenceLen = new int[rows][cols];
			//Each cell will have values - 0 or 1 or 2. 0 means no parent, 1 means left cell is parent, 2 means up cell is parent
			int[][] parent = new int[rows][cols];
			//Initialization
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					maxSequenceLen[i][j] = 1; //There will be atleast 1 item in sequenece ending at any point.
					parent[i][j] = 0;
				}
			}
			max =1;
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					//Check whether current element is part of some sequence coming from left
					if((j>0) && (Math.abs(input[i][j] - input[i][j-1]) == 1)){
						//Now test if we come from left will that sequence be > what we already have
						if(maxSequenceLen[i][j] < (maxSequenceLen[i][j-1]+1)){ //Note the +1 - because we are coming from left we will be increasing sequence len by 1
							maxSequenceLen[i][j] = maxSequenceLen[i][j-1]+1;
							parent[i][j] = 1; //1 indicates left							
						}						
					}
					//Check whether current element is part of some sequence coming from top
					if((i>0) && (Math.abs(input[i][j] - input[i-1][j]) == 1)){
						//Now test if we come from left will that sequence be > what we already have
						if(maxSequenceLen[i][j] < (maxSequenceLen[i-1][j]+1)){ //Note the +1 - because we are coming from top we will be increasing sequence len by 1
							maxSequenceLen[i][j] = maxSequenceLen[i-1][j]+1;
							parent[i][j] = 2; //2 indicates top
						}						
					}
					if(max < maxSequenceLen[i][j])
						max = maxSequenceLen[i][j];
				}
			}
			//Print max sequences
			for(int i=0;i<rows; i++){
				for(int j=0; j<cols; j++){
					if(maxSequenceLen[i][j] == max){
						//Retrace the path
						printPath(input, i, j, parent);
					}
				}
			}
		}
	}
	private static void printPath(int[][] input, int i, int j, int[][] parent){
		List<Integer> sequence = new ArrayList<Integer>();
		sequence.add(input[i][j]);
		while(parent[i][j] != 0){
			if(parent[i][j] == 1){
				//Go Left
				j = j-1;				
			}else{
				//Go up
				i = i-1;
			}
			sequence.add(input[i][j]);			
		}
		Collections.reverse(sequence);
		System.out.println(sequence);
	}
}