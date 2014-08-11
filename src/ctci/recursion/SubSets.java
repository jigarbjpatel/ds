import java.util.ArrayList;
public class SubSets{
	public static void main(String args[]){
		SubSets s = new SubSets();
		ArrayList<Character> set = new ArrayList<Character>();
		set.add('a');
		set.add('b');
		set.add('c');
		set.add('d');
		ArrayList<ArrayList<Character>> subsets = s.subsets(set);		
		for(ArrayList<Character> subset : subsets){
			for(Character c : subset)
				System.out.print(c + " ");
			System.out.println();
		}
	}
	public ArrayList<ArrayList<Character>> subsets(ArrayList<Character> set){
		//Get the subsets of n-1 characters
		//Append nth character to each of existing subset
		//And add this new sub sets to master list
		//Add nth item also to master list (alone)
		ArrayList<ArrayList<Character>> masterList = new ArrayList<ArrayList<Character>>();
		subsets(set.size()-1,set,masterList);
		return masterList;
	}
	private void  subsets(int n, ArrayList<Character> set, ArrayList<ArrayList<Character>> masterList){
		if(n == 0){
			ArrayList<Character> self = new ArrayList<Character>();
			self.add(set.get(n));
			masterList.add(self);
			return;
		}
		subsets(n-1,set,masterList);

		ArrayList<ArrayList<Character>> subsetsForNthItem = new ArrayList<ArrayList<Character>>();
		for(ArrayList<Character> previousList : masterList){
			ArrayList<Character> subsetWithNthItem = new ArrayList<Character>();
			subsetWithNthItem.add(set.get(n));
			subsetWithNthItem.addAll(previousList);	
			subsetsForNthItem.add(subsetWithNthItem);			
		}
		for(ArrayList<Character> subset : subsetsForNthItem)
			masterList.add(subset);
		
		ArrayList<Character> self = new ArrayList<Character>();
		self.add(set.get(n));
		masterList.add(self);	
	}

}
