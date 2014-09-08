import java.util.*;
public class TopKFrequentWords{
	
	//Top K terms out of N words
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);		
		Integer N = Integer.parseInt(scanner.nextLine());
		String[] words = new String[N]; //Storing strings in memory as K is not available until end  
		for(int i=0; i<N; i++){
			words[i] = scanner.nextLine();
		}
		Integer K = scanner.nextInt();
		TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
		topKFrequentWords.printTopK(words, K);		
	}

	public void printTopK(String[] words, int K){
		//Store all the words in a Trie with count and top K Words (along with counts) in a TreeSet
		Trie trie = new Trie();
		TreeSet<WordCount> topKWords = new TreeSet<WordCount>();
		
		for(String word : words){
			Node n = trie.put(word);	
			if(topKWords.contains(new WordCount(word, n.value-1))){
				topKWords.remove(new WordCount(word, n.value-1));
				topKWords.add(new WordCount(word, n.value));
			}else if(topKWords.size() < K){
				topKWords.add(new WordCount(word,n.value));
			}else if(topKWords.first().compareTo(new WordCount(word,n.value)) < 0) {
				topKWords.pollFirst();
				topKWords.add(new WordCount(word,n.value));
			}
		}		
		//trie.print();
		for(WordCount wc : topKWords.descendingSet()){
			System.out.println(wc.word);
		}
	}
	private class WordCount implements Comparable<WordCount>{
		int count;
		String word;
		public WordCount(String word, int count){
			this.count = count;
			this.word = word;
		}
		@Override
		public int hashCode(){
			int result = 17;
			result = 31 * result + word.hashCode();
			result = 31 * result + count;
			return result; 
		}
		@Override
		public boolean equals(Object other){
			if(other == this)
				return true;
			if(!(other instanceof WordCount))
				return false;
			WordCount o = (WordCount)other;
			return this.word.equals(o.word) && this.count == o.count;
		}
		@Override
		public int compareTo(WordCount wc){
			if(this.count == wc.count)
				return wc.word.compareTo(this.word);
			return this.count - wc.count;
		}
		@Override
		public String toString(){
			return this.word + " " + this.count;
		}
	}
	private class Node{
		int value;
		Map<Character,Node> childNodes;
		public Node(){
			value = 0;
			childNodes = new HashMap<Character,Node>();
		}
	}
	private class Trie{		

		private Node root;

		public Trie(){
			root = new Node();
		}
		//Increments value of the node representing last character in a word
		public Node put(String word){
			Node n = root;
			Character index;
			for(int i=0, len = word.length(); i<len; i++){
				index = word.charAt(i);
				if(n.childNodes.get(index) == null)
					n.childNodes.put(index, new Node());
				n = n.childNodes.get(index);			
			}
			n.value++;
			return n;
		}
		//Returns null if the word was never added (it considers full words only - not partial matches)
		public Integer getValue(String word){
			Node n = root;
			for(int i=0, len=word.length(); i<len; i++){
				if(n.childNodes.get(word.charAt(i)) != null)
					n = n.childNodes.get(word.charAt(i));
				else
					return null;
			}
			return n.value != 0 ? n.value : null;
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
			for(Character key : n.childNodes.keySet()){
				
					stack.push(key);
					printNode(n.childNodes.get(key), stack);
					stack.pop();		
				
			}
		}
	}
}