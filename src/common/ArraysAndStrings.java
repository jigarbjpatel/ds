package common;

public class ArraysAndStrings {

	public static void main(String[] args) {
		/*System.out.println(removeString("Hello World From Java","ate"));
		System.out.println(removeString("","ate"));
		System.out.println(removeString("Hello World From Java1.0","1.0"));*/
		System.out.println(reverseString("Abd"));
	}
	public static String reverseString(String str){
		char[] c = str.toCharArray();
		int start  = 0;
		int end = str.length()-1;
		while(end>start){
			char temp = c[start];
			c[start] = c[end];
			c[end] = temp;
			start++;
			end--;
		}
		return new String(c);
	}
	/**
	 * Removes characters from one string from another string
	 * @param input The input string from which the characters will be removed
	 * @param remove The string containing characters to remove
	 * @return the input string minus the characters removed
	 */
	public static String removeString(String input, String remove){
		char[] inputArray = input.toCharArray();
		char[] removeArray = remove.toCharArray();
		boolean[] flags = new boolean[128]; //asumes ASCII input
		int srcPointer = 0;
		int destPointer = 0;
		//set up the remove array flags
		for(int i=0; i<removeArray.length; i++){
			flags[removeArray[i]] = true; //char is treated as index in array as chars are integers in some
		}
		//compare the input string
		for(int i=0;i<inputArray.length; i++){
			if(flags[inputArray[i]] == false){
				inputArray[destPointer] = inputArray[srcPointer];
				destPointer++;
			}
			srcPointer++;
		}
		
		return new String(inputArray,0,destPointer);
	}
}
