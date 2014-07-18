package common;
import java.util.*;
public class LargestSum{

	public static void main(String args[]){
		int[] input = {-10,3,4,-2,-5,-10,7,8};
		System.out.println(largestSum(input));
	}
	public static List<Integer> largestSum(int[] input){
		List<Integer> seq = new ArrayList<Integer>();
		int maxSumStart = -1;
		int maxSumEnd = 0;
		int runningSumStart = 0;
		int runningSum =0;
		int maxSum = -1;
		for(int i=0; i<input.length; i++){
			runningSum += input[i];
			if(runningSum < 0){
				runningSum =0;
				runningSumStart = i+1;
			}else if(runningSum > maxSum){
				maxSum = runningSum;
				maxSumStart = runningSumStart;
				maxSumEnd = i;
			}
		}
		if(maxSumStart!=-1){
			for(int i=maxSumStart; i<=maxSumEnd; i++)
				seq.add(input[i]);
		}
		return seq;
	}
}
