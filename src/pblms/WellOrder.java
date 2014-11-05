//Prints all well ordered numbers given a number of digits.
//This problem is similar to printg all permutations 
public class WellOrder{
	public static void main(String[] args){
		//Print all well order numbers of size 3
		printWellOrdered("",-1,3);
	}
	//Param number - is the current output (soFar)
	//Param n - is like (rest) which sepcifies what is left to be done (the digit to generate) & stops the recursion
	//Param prev - this is specific to this problem as we need a well ordered number. We need to check what is the previous number
	private static void printWellOrdered(String number, int prev, int n) { 

		if(n==0){ 
			System.out.println(number); 
			return; 
		} 

		for(int i=(prev+1); i<(11-n); i++){ 
			printWellOrdered(number+i, i, n-1) ; 
		} 

	}

}