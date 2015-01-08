import java.util.*;
public class threeSum{
	public static void main(String[] args){
        int[] input = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> res = getThreeSum(input);
		for(List<Integer> triplet : res){
			for(Integer i : triplet)
				System.out.print(i + " ");
			System.out.println();
		}
	}
	 public static List<List<Integer>> getThreeSum(int[] num) {
        int n = num.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n>2){
            //Sort the input
            Arrays.sort(num);
            for(int i=0; i<n-2; i++){
                //Compare with prev - if same then ignore
                if(i==0 || num[i]!=num[i-1]){
                    int left = -num[i];
                    //Problem is reduced to 2 Sum in the remaining array
                    int l = i+1;
                    int r = n-1;
                    while(l < r){
                        if(l != i+1 && num[l] == num[l-1]){
                            l++;
                        }else if(num[l] + num[r] == left){
                            //Found one triplet - put it in result
                            List<Integer> triplet = new ArrayList<Integer>();
                            triplet.add(num[i]);
                            triplet.add(num[l]);
                            triplet.add(num[r]);
                            result.add(triplet);
                            //Keep looking for other triplets
                            l++;
                            r--;
                        }else if(num[l] + num[r] < left){
                               l++;
                        }else{
                            r--;
                        }
                    }
                }
            }
        }
        return result;
    }
}