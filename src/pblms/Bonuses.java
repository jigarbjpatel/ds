	package pblms;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Bonuses {

	public static void main(String[] args) {
		Bonuses b = new Bonuses();
		int[] points = { 1,2,4,4,4,4, 5};
		int[] percents = b.getDivision(points);
		for(int i=0; i<percents.length; i++)
			System.out.println(percents[i]);
	}
	public int[] getDivision(int[] points){
		int[] res = new int[points.length];
		int totalPoints = 0;
		TreeMap<Integer,TreeSet<Integer>> pointsMap = new TreeMap<Integer,TreeSet<Integer>>();
		for(Integer i=0; i<points.length; i++){
			TreeSet<Integer> indexSet = pointsMap.get(points[i]);
			if(indexSet != null)
				indexSet.add(i);
			else{
				indexSet = new TreeSet<Integer>();
				indexSet.add(i);
				pointsMap.put(points[i], indexSet);
			}
			totalPoints += points[i];		
		}
		int totalPercent = 0;
		for(int i=0; i<points.length; i++){
			res[i] = (points[i]* 100 / totalPoints) ;
			totalPercent += res[i];
		}
		
		int left = 100 - totalPercent;
		TreeSet<Integer> sameEntries = null;
		while(left != 0){
			Entry<Integer,TreeSet<Integer>> last = pointsMap.pollLastEntry();			
			sameEntries = last.getValue();
			while(left !=0 && sameEntries.size() != 0){
				res[sameEntries.pollFirst()] += 1;
				left--;
			}
		}
		return res;
	}
	
}


	
