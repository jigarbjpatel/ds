package common;
public class SubSets{
	public static void main(String args[]){
		ListSubSets("abc");
	}
	public static void ListSubSets(String input){
		ListSubSets("", input);
	}
	private static void ListSubSets(String soFar, String rest){
		if(rest.equals("")) //If there is nothing left to include print it
			System.out.println(soFar);
		else{
			//Include first item from rest and recurse
			//Remember that we need to append the included item to existing inclusion (soFar)
			ListSubSets(soFar + rest.charAt(0), rest.substring(1));

			//Do not include first item and recurse
			ListSubSets(soFar, rest.substring(1));
		}
	}

}
