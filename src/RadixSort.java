package edu.cmu.jjpatel;
import java.util.*;
public class RadixSort{
	public static void sort(String[] data,int maxLen){
		final int BUCKETS = 256;
		ArrayList<String>[] buckets = new ArrayList[BUCKETS]; //<> becuase of generic error creation issue
		ArrayList<String>[] wordsByLength = new ArrayList[maxLen+1];//+1 to take care of 0-length strings
		//Initialize both the arrays
		for(int i=0; i<wordsByLength.length; i++)
			wordsByLength[i] = new ArrayList<String>();
		for(int i=0; i<BUCKETS; i++)
			buckets[i] = new ArrayList<String>();
		//put all string as per word length using Bucket Sort
		for(int i=0; i<data.length; i++)
			wordsByLength[data[i].length()].add(data[i]);
		int idx=0;
		for(ArrayList<String> wordList : wordsByLength)
			for(String s : wordList)
				data[idx++] = s;
		//data contains all the strings sorted as per word length
		int startingIndex = data.length;
		//Total passes = max number of characters in a string
		for(int pos = maxLen-1; pos >= 0; pos--){
		//Each iteration 
			//Get the index of strings of length pos+1
			startingIndex = startingIndex - wordsByLength[pos + 1].size(); 
			//add strings to buckets based on character position (here pos is 0 based as String is 0 based array)
			for(int i=startingIndex; i<data.length; i++)
				buckets[data[i].charAt(pos)].add(data[i]);
			//copy paritally sorted data from buckets to the data array (take care of idx)
			idx = startingIndex; 
			for(ArrayList<String> bucket : buckets){
				for(String s : bucket)
					data[idx++] = s;
				//clear the buckets for next pass		
				bucket.clear();
			}
			for(int i=0;i<data.length;i++)
				System.out.print(data[i] + " ");	
			System.out.println();
			
		}
	}
	public static void main(String args[]){
		String[] data = new String[5];
		data[0] = "a";
		data[1] = "ca";
		data[2] = "b";
		data[3] = "ba";
		data[4] = "cd";
		for(int i=0; i<5; i++)
			System.out.print(data[i] + " ");
		System.out.println();
		RadixSort.sort(data,2);
		for(int i=0; i<5; i++)
			System.out.print(data[i] + " ");
	}
}
