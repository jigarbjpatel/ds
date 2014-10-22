import java.util.*;
// Find minimum distance between two words in the sentence.

public class WordDistance{
	
	public static void main(String args[]){
		List<String> words = new ArrayList<String>();
		String input = "the quick black bad fox jumped over the grey fox";
		words.addAll(Arrays.asList(input.split(" ")));
		WordDistance wd = new WordDistance(words);
		System.out.println(wd.distance("the","quick"));
		System.out.println(wd.distance("the","fox"));
	}

	private HashMap<String,ArrayList<Integer>> wordPositions;
	public WordDistance(List<String> words){
		wordPositions = new HashMap<String,ArrayList<Integer>>();
		String s;
		ArrayList<Integer> positions;
		for(int i=0; i<words.size(); i++){
			s = words.get(i);
			if(wordPositions.get(s) != null){
				positions = wordPositions.get(s);
				positions.add(i);
			}else{
				positions = new ArrayList<Integer>();
				positions.add(i);
				wordPositions.put(s, positions);
			}
		}
	}
	//Time complexity - O(n) where n is the number of times both the words appear in sentence
	public int distance(String wordOne, String wordTwo){
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> one = wordPositions.get(wordOne);
		ArrayList<Integer> two = wordPositions.get(wordTwo);
		int lastCheckedPosition = 0;
		int positiveDiff;
		int negativeDiff;
		for(int i=0; i<one.size(); i++){
			positiveDiff = Integer.MAX_VALUE;
			negativeDiff = Integer.MAX_VALUE;
			for(int j=lastCheckedPosition; j<two.size(); j++){			
				if((two.get(j) - one.get(i)) < 0){					
					negativeDiff = two.get(j) - one.get(i);
				}else{
					lastCheckedPosition = j;
					positiveDiff = two.get(j) - one.get(i);
					break; // We found a positive difference
				}
			}		
			if(Math.abs(negativeDiff) < positiveDiff){
				if(Math.abs(negativeDiff) < min)
					min = Math.abs(negativeDiff);				
			}else{
				if(positiveDiff < min)
					min = positiveDiff;
			}
		}
		return min;
	}
}