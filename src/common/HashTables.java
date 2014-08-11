package common;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author Jigar
 *
 */
public class HashTables {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] values = new String[]{"one","two","three","four"};
		HashMap<Integer,String> map = build(values);
		print(map);

		System.out.println(returnFirstNonRepeatedCharacter("ddsdfds"));
		//map.containsKey(1);
		//map.entrySet()
	}
	/**
	 * Returns first non repeated Character from a given string (using HashMap)
	 */
	private static Character returnFirstNonRepeatedCharacter(String s) {
		HashMap<Character,Integer> t = new HashMap<Character,Integer>();
		Character c;
		Integer count;
		for(int i=0; i<s.length();i++){
			c = s.charAt(i);
			count = t.get(c);
			if(count!=null){
				t.put(c, count+1);
			}else{
				t.put(c, 1);
			}
		}
		for(int i=0; i<s.length(); i++){
			c = s.charAt(i);
			if(t.get(c) == 1){
				return c;
			}
		}
		return null;
	}
	public static HashMap<Integer,String> build(String[] values){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		for(int i=0;i<values.length;i++){
			map.put(i, values[i]);
		}
		return map;		
	}
	public static void print(HashMap<Integer,String> map){
		for(Integer key : map.keySet()){
			System.out.println(map.get(key));
		}
	}
}
