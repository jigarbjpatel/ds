package common;
import java.util.*;

public class JavaCollections{

	public static void main(String args[]){
		LinkedList<String> stack = new LinkedList<String>();
		stack.push("tet");
		stack.pop();
		System.out.println(stack.size());
	}
	public static void usingArrayList(){
		ArrayList<String> al = new ArrayList<String>();
		al.add("set");
		al.add("list");
		al.add("set");
		al.remove("set");
		System.out.println(al.size());
		System.out.println(al.contains("set"));
		for(String s : al)
			System.out.println(s);
	}
	public static void usingHashSets(){
		HashSet<String> hs = new HashSet<String>();
		hs.add("test");
		hs.add("test1");
		hs.remove("test");
		System.out.println(hs.size());
		System.out.println(hs.contains("test"));
		for(String s : hs)
			System.out.println(s);
	}

}
