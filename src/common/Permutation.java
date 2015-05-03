package common;
public class Permutation{
	public static void main(String args[]){
		Permute("abc");
	}
	public static void Permute(String input){
		Permute("", input);
	}
	private static void Permute(String soFar, String rest){
		//If there is nothing left to permute print whatever is accumulated
		if(rest.equals(""))
			System.out.println(soFar);
		else{
			//Choose for first position, then second position and so on
			for(int i=0; i<rest.length(); i++){
				//choosing ith character (append it to what is already choosen)
				String nextChoice = soFar + rest.charAt(i); 
				//Now rest would be what is not choosen i.e. exclude ith Character from rest
				//remember that soFar is already out of rest
				String remaining = rest.substring(0,i) + rest.substring(i+1);
				//Recurse
				Permute(nextChoice, remaining);
			}
		}
	}
}
