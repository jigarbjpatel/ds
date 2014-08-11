import java.util.HashMap;
import java.util.HashSet;
public class UniqueString{
	public static void main(String args[]){
		String s = args[0];
		System.out.println(isUnique(s));
		System.out.println(isUniqueUsingBitVector(s));
		System.out.println(isUniqueUsingHashSet(s));
		System.out.println(isUniqueUsingHashMap(s));
		
	}
	private static boolean isUnique(String s){
		boolean[] chars = new boolean[256];
		for(int i=0; i<s.length(); i++){
			if(chars[s.charAt(i)])
				return false;
			else
				chars[s.charAt(i)] = true;
		}
		return true;
	}
	private static boolean isUniqueUsingBitVector(String s){
		int bitVector = 0;
		for(int i=0; i<s.length(); i++){
			int asciiValue = s.charAt(i) - 'a'; //asciiValue between 0 and 25
			if((bitVector & (1 << asciiValue)) > 0) // left shift 1 by ascii value -> multiply 1 by power(2,asciivalue) 
				return false;
			bitVector = bitVector | (1 << asciiValue);
		}
		return true;
	}
	private static boolean isUniqueUsingHashSet(String s){
		HashSet<Character> chars = new HashSet<Character>();
		for(int i=0; i<s.length(); i++){
			if(!chars.add(s.charAt(i)))
				return false;
		}
		return true;
	}
	private static boolean isUniqueUsingHashMap(String s){
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		for(int i=0;i<s.length(); i++){
			if(map.get(s.charAt(i)) != null && map.get(s.charAt(i))== 1){
				return false;
			}
			map.put(s.charAt(i),1);
		}
		return true;	
	}

}
