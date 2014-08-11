import java.util.Scanner;
public class ReplaceChar{

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String charToReplace = in.next();
		String replacementChar = in.next();
		System.out.println(replaceChar(s,charToReplace.charAt(0),replacementChar));
	}
	public static String replaceChar(String s, Character charToReplace, String replacementChar){
		int rlen = replacementChar.length();
		char[] chars = s.toCharArray();
		int  numberOfCharsToReplace = 0;
		for(int i=0; i<chars.length; i++)
			if(chars[i] == charToReplace)
				numberOfCharsToReplace++;	 	
		int newLen = numberOfCharsToReplace * (rlen-1) + s.length();
		char[] newChars = new char[newLen];
		//Important - start from end
		for(int i=chars.length-1; i>=0; i--){
			if(chars[i] != charToReplace){
				newChars[--newLen] = chars[i];
			}else{
				for(int j=replacementChar.length()-1;j>=0; j--)
					newChars[--newLen] = replacementChar.charAt(j);
			}
		}
		return new String(newChars);
	}


}
