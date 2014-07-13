package common;
import java.util.*;
public class Anagram{
	public static void main(String args[]){
		String[] s = new String[]{"nozzle","punjabi","waterlogged","imprison","crux","numismatists","sultans","rambles","deprecating","aware","outfield"};
		System.out.println(anagrams(s)	);
	}
	public static ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> res = new ArrayList<String>();
		if(strs.length == 0)
			return res;
		boolean[] done = new boolean[strs.length];
		int anchor = 0;
		int i = 0;
		boolean gotOne = false;
		int n = 0;
		while(n < strs.length-1){
			gotOne = false;
			anchor = findAnchor(n,done);
			System.out.println(anchor);
			if(anchor == -1)
				break;
			for(i = anchor+1; i < strs.length; i++){
				//                System.out.println(isAnagram(strs[anchor], strs[i]));
				if(isAnagram(strs[anchor], strs[i])){
					done[i] = true;
					res.add(strs[i]);
					gotOne = true;
				}
			}
			if(gotOne)
				res.add(strs[anchor]);
			n++;
		}
		return res;
	}
	public static int findAnchor(int i, boolean[] done){
		for(; i<done.length; i++){
			if(!done[i])
				break;
		}
		if(i == done.length)
			return -1;
		else
			return i;
	}
	public static boolean isAnagram(String a, String b){
		if(a.length() != b.length())
			return false;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		Character c = null;
		Integer count = null;
		for(int i=0; i<a.length(); i++){
			c = a.charAt(i);
			count = map.get(c);
			if(count != null)
				map.put(c, ++count);
			else
				map.put(c, 1);
		}

		for(int i =0 ; i<b.length(); i++){
			c = b.charAt(i);
			count = map.get(c);
			//System.out.println(c + " " +count);
			if(count == null || count == 0)
				return false;
			else
				map.put(c, --count);
		}
		//System.out.println(map);
		for(Map.Entry<Character,Integer> e : map.entrySet()){
			if(e.getValue() > 0)
				return false;
		}
		return true;
	}
}
