import java.util.*;

public class IsAnagram{

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String src = in.next();
		String dest = in.next();
		System.out.println(isAnagram(src,dest));
	}
	public static boolean isAnagram(String src, String dest){
		if(src.length() != dest.length())
			return false;
		//Use character count in src and dest and compare
		//For ASCII use boolean array of size 256 to store character count 
		//For Unicode use HashMap
		HashMap<Character,Integer> charMap = new HashMap<Character,Integer>();
		for(int i=0; i<src.length(); i++){
			Character c = src.charAt(i);
			Integer count = charMap.get(c); 
			if(count != null)
				charMap.put(c,++count);
			else
				charMap.put(c,1);
		}
		for(int i=0; i<dest.length(); i++){
			Character c = dest.charAt(i);
			Integer count = charMap.get(c);
			if(count == null)
				return false;
			else
				charMap.put(c,--count);
		}
		for(Character c : charMap.keySet()){
			if(charMap.get(c) != 0)
				return false;
		}
		return true;
	}
}
