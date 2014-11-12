import java.util.*;
public class MinOverMovingWindow{
	
	public static void main(String[] args){
		int[] input = {3,6,8,4,1,5,8,9};
		int window = 3;
		printMinOVerMovingWindow(input, window);
	}
	
	public static void printMinOVerMovingWindow(int[] input, int window){
		TreeSet<Integer> sortedSet = new TreeSet<Integer>(); // Event PriorityQueue can be used - Time Complexity is O(N*logW)

		int N = input.length;
		if(N < window || window <= 0 || N == 0)
			return;
		for(int i=0; i<=(N-window); i++){
			if(i == 0){
				//Put first W elements in the tree
				for(int j=0; j<window; j++)
					sortedSet.add(input[j]);					
			}else{
				sortedSet.add(input[i+window-1]);
			}
			System.out.println(sortedSet.first());

			sortedSet.remove(input[i]);
			//If we use MinHeap then go for lazy delete - for that we need to store pairs (element and index) in heap

			//Best solution is to use a Dequeue - O(N)
		}
	}
}