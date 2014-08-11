package pblms;

public class VendingMachine {

	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine();
		String[] prices = {"100 200 300",
		 "600 500 400"};
		String[] purchases = {"0,0:0", "1,1:10", "1,2:20",
				 "0,1:21", "1,0:22", "0,2:35"};
		System.out.println(vm.motorUse(prices,purchases));
	}
	int currCol = 0;
	boolean[][] sold;
	int rows,cols;
	int[][] price;
	public int motorUse(String[] prices, String[] purchases){
		int res=0;
		rows = prices.length;
		cols = prices[0].split(" ").length;
		price = new int[rows][cols];
		String[] cells;
		for(int i=0; i<rows; i++){
			cells = prices[i].split(" ");
			for(int j=0; j<cols; j++){
				price[i][j] = Integer.parseInt(cells[j]);
			}
		}
		sold = new boolean[rows][cols];
		
		//First move - Get the max priced col and go there (count how far it is)
		res+=rotateToMostExpensive();
		
		//Loop thru all purchases
		String[] temp;
		int shelf,col,time = 0;
		for(int p=0; p<purchases.length; p++){
			temp = purchases[p].split("[,:]");
			//Check if we need to rotate for displaying most expensive col
			if(p != 0 && (Integer.parseInt(temp[2]) - time) >= 5){
				res+=rotateToMostExpensive();
			}
			//Purchase transaction
			shelf = Integer.parseInt(temp[0]);
			col = Integer.parseInt(temp[1]);
			time = Integer.parseInt(temp[2]);
			if(sold[shelf][col] == true)
				return -1;
			int rotateLeft = Math.abs(currCol - col);
			int rotateRight = col > currCol ? (cols-col)+(currCol) : (cols-currCol)+(col) ;
			res += rotateLeft > rotateRight ? rotateRight : rotateLeft;
			currCol = col;
			sold[shelf][col]= true;
		}
		//Final move
		res += rotateToMostExpensive();
		return res;
	}
	public int rotateToMostExpensive(){
		int max=0;
		int maxCol = 0;
		int sum=0;
		for(int j=0; j<cols; j++){
			sum = 0;
			for(int i=0; i<rows; i++)
				if(!sold[i][j])
					sum += price[i][j];
			if(max < sum){
				max = sum;
				maxCol = j;
			}
		}
		int rotateLeft = Math.abs(currCol-maxCol);
		int rotateRight = maxCol > currCol ? (cols-maxCol)+(currCol) : (cols-currCol)+(maxCol) ;
		
		currCol = maxCol;
		return rotateLeft > rotateRight ? rotateRight : rotateLeft;
	}
}
