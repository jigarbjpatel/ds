import java.util.*;
class AnagramComparator implements Comparator<String>{

	public int compare(String s1, String s2){
		//sort s1 and s2
		char[] s1sorted = s1.toCharArray();
		Arrays.sort(s1sorted);
		s1 = new String(s1sorted);
		char[] s2sorted = s2.toCharArray();
		Arrays.sort(s2sorted);
		s2 = new String(s2sorted);
		return s1.compareTo(s2);
	}

}

public class AnagramSorting{
	public static void main(String args[]){
		String[] input = new String[]{"abc","rdf","bac","oooo","dfr","sss"};
		Arrays.sort(input,new AnagramComparator());
		for(String s : input)
			System.out.println(s);
	}


}
