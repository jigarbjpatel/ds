import java.util.Scanner;

public class IsPerfectNumber{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.next());
		System.out.println(isPerfectNumber(n));
	}
	public static boolean isPerfectNumber(int n){
		int sum = 1;
		for(int i=2; i<Math.sqrt(n); i++){
			if(n%i == 0)
				sum += i + (n/i);
		}
		return sum == n;
	}

}
