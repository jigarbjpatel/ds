package common;
import java.util.Scanner;
public class BinaryToInt{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		System.out.println(getInt(s));
	}
	public static Integer getInt(String s){
		Integer sum = 0;
	        int j = s.length()-1;
        	for(int i=0; i<s.length() && j>=0; i++, j--){
	            sum += (int)Math.pow(2,i) * Character.getNumericValue(s.charAt(j));

        	}
	        return sum;
	}
}
