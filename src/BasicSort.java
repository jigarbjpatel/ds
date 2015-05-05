package edu.cmu.jjpatel;
//All in ascending order
public class BasicSort{
	//Basic idea is find the correct element for current index (by using min variable) and then swap
	//Total O(n) swaps only but O(n^2) comparisions
	public void selectionSort(Integer[] data){
		int minIndex = 0;
		int n = data.length;
		//last element is already in its place after n-1 passes
		for(int i=0; i < n-1; i++){
			minIndex = i;
			for(int j=i+1; j < n; j++){
				if(data[j] < data[minIndex])
					minIndex = j;
			}
			swap(data,i,minIndex);
		}
	}
	//Basic idea - compare adjacent elements and keep on swaping larger element with smaller element
	//In each pass, the largest element is placed at its correct position i.e. end values are in their proper places. 
	//O(n^2) in worst case but in place. O(n) if the array is nearly sorted (only when we use a flag)
	public void bubbleSort(Integer[] data){
		boolean swapped = false; 
		int n = data.length;
		for(int i=0; i < n; i++){
			//total n-i-1 comparisions in innerloop 
			for(int j=0; j < n-i-1; j++){
				if(data[j] > data[j+1]){
					swap(data,j,j+1); 
					swapped = true;
				}
			}
			if(!swapped) 
				break; //If no swap made in current pass then no need for further passes
		}
	}
	//Take an element and compare with all previous elements until u find a smaller element
	//It is basically shell sort with gap = 1.
	//Good for small data and partially sorted data almost O(n)
	//Also a stable sort 
	public void insertionSort(Integer[] data){
		int j;
		int n = data.length;
		for(int i=1; i < n; i++){
			Integer currentValue = data[i];
			
			for(j=i-1; (j>=0) && (currentValue < data[j]); j=j-1)
				data[j+1] = data[j];//shift to right
			
			data[j+1] = currentValue; //place the current value in correct place relative to previous values
		}
	}
	//h-sort - sort at intervals defined by gap
	//Used in embedded systems can be O(n^4/3) for particular gap sequence othereise O(n^2)
	public void shellSort(Integer[] data){
		int n = data.length;
		for(int gap = n/2; gap > 0; gap = gap/2){
			//Do an insertion sort
			int j;
			for(int i = gap; i < n; i++){
				Integer currentValue = data[i];
				for(j=i-gap; j>=0 && currentValue < data[j]; j=j-gap)
					data[j+gap] = data[j];
				data[j+gap] = currentValue;
			}
		}
	}
	public void swap(Integer[] data, int from, int to){
		Integer temp = data[from];
		data[from] = data[to];
		data[to] = temp;
	}
	
	public static void main(String args[]){
		BasicSort bs = new BasicSort();
		Integer[] data = new Integer[10];
		for(int i=0; i<10; i++)
			data[i] = 10-i;
	//	bs.selectionSort(data);
	//	bs.bubbleSort(data);
	//	bs.insertionSort(data);
		bs.shellSort(data);
		for(int i=0; i<data.length; i++)
			System.out.println(data[i]);
	}
}
