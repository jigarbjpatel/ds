import java.util.*;

public class Trie{

	private class Node{
		int value;
		Node[] childNodes;
		public Node(){
			value = 0;
			childNodes = new Node[26]; //Works only for a-z characters
		}		
	}

	private Node root;

	public Trie(){
		root = new Node();
	}

	// Overwrites the value for node representing last character in word. 
	// For rest of the nodes, the value would remain same as previous.
	public Node put(String word, int value){
		Node n = root;
		int index = 0;
		for(int i=0, len = word.length(); i<len; i++){
			index = word.charAt(i) - 'a';
			if(n.childNodes[index] == null)
				n.childNodes[index] = new Node();
			n = n.childNodes[index];			
		}
		n.value = value;
		return n;
	}

	public void print(){
		Node n = root;
		java.util.LinkedList<Character> stack = new java.util.LinkedList<Character>();
		printNode(n,stack);
	}
	private void printNode(Node n, java.util.LinkedList<Character> stack){
		if(n.value != 0){
			Iterator reverseIterator = stack.descendingIterator();
			while(reverseIterator.hasNext()){
				System.out.print(reverseIterator.next());
			}
			System.out.println(" - " + n.value);
		}
		for(int i=0; i<26; i++){
			if(n.childNodes[i] != null){
				stack.push((char)('a' + i));
				printNode(n.childNodes[i], stack);
				stack.pop();		
			}
		}
	}

	public Integer getValue(String word){
		Node n = root;
		for(int i=0, len=word.length(); i<len; i++){
			if(n.childNodes[word.charAt(i) - 'a'] != null)
				n = n.childNodes[word.charAt(i) - 'a'];
			else
				return null;
		}
		return n.value != 0 ? n.value : null;
	}
	public static void main(String args[]){
		Trie t = new Trie();
		t.put("word",1);
		t.put("wordf",2);
		t.put("wor",3);
		t.put("worde",1);
		
		t.print();

		System.out.println(t.getValue("worde"));
	}
}