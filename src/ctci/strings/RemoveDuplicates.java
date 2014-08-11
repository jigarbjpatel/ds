import java.util.*;
public class RemoveDuplicates{

	public static void main(String args[]){
		String s = args[0];
		//O(n) time and constant space
		System.out.println(removeDuplicates(s.toCharArray()));
		//O(n*n) time and no space
		System.out.println(removeDuplicatesWithOutMemory(s.toCharArray()));
		//O(nlogn) time and no space
		char[] c = s.toCharArray();
		Arrays.sort(c);
		int tail=1, prev=0, curr=0;
		for(int i=1; i<c.length; i++){
			if(c[i] != c[prev]){
				prev=i;
				c[tail] = c[i];
				tail++;
			}
		}
		System.out.println(new String(c,0,tail));
	}
	public static String removeDuplicates(char[] s){
		boolean[] chars = new boolean[256];
		int lastNonDuplicateChar = 0;
		for(int i=0; i<s.length; i++){
			if(!chars[s[i]]){
				chars[s[i]] = true;
				s[lastNonDuplicateChar] = s[i];
				lastNonDuplicateChar++;
			}
		}
		return new String(s,0,lastNonDuplicateChar);
	}
	private static String removeDuplicatesWithOutMemory(char[] s){
		int nextUniqueChar = 1;
		int j=0;
		for(int i=1; i<s.length; i++){
			for(j=0; j<nextUniqueChar; j++){
				if(s[j] == s[i])
					break;
			}
			if(j == nextUniqueChar){
				s[nextUniqueChar] = s[i];
				nextUniqueChar++;
			}
		}
		return new String(s,0,nextUniqueChar);
	}
}
