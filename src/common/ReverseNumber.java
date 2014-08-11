package common;
import java.util.Scanner;
public class ReverseNumber{

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		System.out.println(reverse(String.valueOf(number)));
	}

	public static String reverse(String number){
		char[] n = number.toCharArray();
		int len = n.length;
		char temp;
		for(int i=0, j=len-1; i < j; i++, j--){
			temp = n[i];
			n[i] = n[j];
			n[j] = temp;	
		}

		return new String(n);
	}
}
