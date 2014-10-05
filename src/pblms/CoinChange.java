public class CoinChange{
	public static void main(String[] args){	

		int[] input = {1,2,3};
		System.out.println(getCount(input,5));
		System.out.println(getCountDP(input,5));
	}
	private static int getCountDP(int[] input, int sum){
		// row represents sum required (from 0 to 5)
		// and col represents set of elements (first col represents only 1 element, second col represents both 1st and 2nd element and so on)
		int[][] dpTable= new int[sum+1][input.length];
		//init first row of the table - there is 1 solution for getting sum of 0 with any number of elements
		for(int i=0; i<input.length; i++)
			dpTable[0][i] = 1;
		int countWhenIncludingElement, countWhenExcludingElement;
		for(int row=1; row<sum+1; row++){
			for(int col=0; col<input.length; col++){
				//When input[col] is included we reduce the pblm to getting row-input[col] (row represents sums and col represents set of elements)
				countWhenIncludingElement = (row - input[col]) >=0 ? dpTable[row-input[col]][col] : 0;
				//When excluding, we reduce pblm to getting same sum but with 1 less element and we can copy from previus colum in same row
				countWhenExcludingElement = col >= 1 ? dpTable[row][col-1] : 0;
				dpTable[row][col] = countWhenExcludingElement + countWhenIncludingElement;
			}
		}
		return dpTable[sum][input.length-1];
	} 
	// Recursive solution
	private static int getCount(int[] input, int sum){
		if(input.length == 0) 
			return 0;
		if(sum ==0 ) 
			return 1; //Do not include any number
		if(sum < 0)//Assuming non-negative numbers
			return 0;

		//Include last element as part of solution
		int r = getCount(input, sum-input[input.length-1]);
		//Do not include last element as part of solution
		int[] newInput = new int[input.length-1];
		for(int i=0; i<input.length-1; i++)
			newInput[i] = input[i];
		
		int l = getCount(newInput, sum);
		return l + r;
	}

}