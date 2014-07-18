package common;
public class BitMagic{

	public static void main(String args[]){
		System.out.println(countOnes(-1));
		
	}
	private static int countOnes(int n){
		int count = 0;
		/*while(n!=0){
			if(n%2==1)
				count++;
			n = n >>> 1;
		}*/
		
		/*while(n!=0){
			count += n & 1; //will add 1 or 0 depending on whether last bit is 1 or 0
			n = n >>> 1;
		}*/
		
		while(n!=0){
			n = n & (n-1);
			count++;
		}
		return count;
	}
}

