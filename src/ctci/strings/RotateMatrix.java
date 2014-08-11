import java.util.*;
public class RotateMatrix{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[][] image = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				image[i][j] = j+1+(N*i);
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++)
				System.out.print(image[i][j]+" ");
			System.out.println();
		}

		rotate90(image,N);
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++)
				System.out.print(image[i][j]+" ");
			System.out.println();
		}
	}
	public static void rotate90(int[][] image,int N){
		//rotate in layers - there will be total N/2 layers in any even matrix -
			// for odd matrices, ignore the innermost layer as it will always be in center
		for(int layer=0; layer<N/2; layer++){
			//get the first and last indices(both for row and col) for the layer
			int first = layer;
			int last = (N-1)-layer; //-layer because last will move to left by one col and move up by one row in each round of swapping
			//Within each layer swapping will be done one by one - from corner elements to next elements and so on
			//Do actual swapping from first to last element
			for(int i=first; i<last; i++){
				//save top left 
				int topLeft = image[first][i]; //i is used as column index
				//move bottomleft to topleft
				image[first][i] = image[last-(i-first)][first]; //its column is fixed but row keeps on changing
				//move bottomright to bottomleft
				image[last-(i-first)][first] = image[last][last-(i-first)]; //column is changing but row is always last
				//move topright to bottomright
				image[last][last-(i-first)] = image[i][last];
				//restore topleft to topright
				image[i][last] = topLeft;
			}
		}
	}
	public static void rotate180(int[] image, int N){

	}
}
