import java.util.Scanner;
public class Fibonacci{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println("nth FibonacciNumber = " + fn(n));
		System.out.println("iteratively = " + fni(n));
	}
	private static int fn(int n){
		if(n<0)
			return -1;
		else if(n == 0)
			return 0; //f(0) = 0
		else if(n == 1)
			return 1; //f(1) = 1
		else 
			return fn(n-1) + fn(n-2);		
	}
	private static int fni(int n){
		if(n < 0)
			return -1;
		else if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else{
			int last = 1; int lastToLast = 0;
			int result = 0;
			for(int i=2; i<=n; i++){
				result = last + lastToLast;
				lastToLast = last;
				last = result;
			}
			return result;
		}
	}

}
