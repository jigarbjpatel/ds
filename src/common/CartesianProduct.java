package common;
import java.util.*;
public class CartesianProduct{
	public static void main(String[] args){
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0;i<3; i++)
			temp.add(i);
		input.add(temp);

		temp = new ArrayList<Integer>();

		for(int i=4;i<6;i++)
			temp.add(i);
		input.add(temp);

		temp = new ArrayList<Integer>();
		for(int i=7;i<10; i++)
			temp.add(i);
		input.add(temp);

		ArrayList<ArrayList<Integer>> output = getProduct(input);
		

		System.out.println(output);
	}
	public static ArrayList<ArrayList<Integer>> getProduct(ArrayList<ArrayList<Integer>> input){
		if(input.size() <= 1)
			return input;
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0; i<input.get(0).size(); i++){
			temp = new ArrayList<Integer>();
			temp.add(input.get(0).get(i));
			output.add(temp);
		}

		return getProduct(input,output,1);


	}	
	private static ArrayList<ArrayList<Integer>> getProduct(ArrayList<ArrayList<Integer>> input, ArrayList<ArrayList<Integer>> currOutput, int i){
		ArrayList<ArrayList<Integer>> newOutput = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		ArrayList<Integer> currentList = input.get(i);
		for(int j=0; j<currentList.size(); j++){
			for(int k=0; k<currOutput.size(); k++){

				tempList = (ArrayList<Integer>)currOutput.get(k).clone();
				tempList.add(currentList.get(j));
				newOutput.add(tempList);

			}
		}
		if(i+1==input.size())
			return newOutput;		
		else 
			return getProduct(input,newOutput,i+1);
	}

}
