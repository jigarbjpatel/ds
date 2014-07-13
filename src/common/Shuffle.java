package common;
public class Shuffle{
public static int N = 5;
	public static void main(String args[]){
		int[] input = new int[N];
		for(int i=0; i<N; i++)
			input[i]=i;
		shuffleArray(input);
		
		for(int i=0; i<N; i++)
			System.out.print(input[i] + " ");
	}
	public static void shuffleArray(int[] input){
		int ranIndex;
		int temp;
		for(int i=0; i<N; i++){
			ranIndex = (int)(Math.random() * (N-i));
			temp = input[ranIndex];
			input[ranIndex] = input[N-i-1];
			input[N-i-1] = temp;
		}
	}
}
