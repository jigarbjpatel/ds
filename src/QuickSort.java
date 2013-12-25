package edu.cmu.jjpatel;

public class QuickSort{

	public void sort(Integer[] data){
		sort(data,0,data.length-1);
	}
	public void sort(Integer[] data, int low, int high){
		if((high-low) < 10){
			insertionSort(data,low,high);
			return;
		}
		int pivotIndex = partition(data,low,high);
		sort(data,low,pivotIndex-1);
		sort(data,pivotIndex+1,high);
	}
	public int partition(Integer[] data, int low, int high){
		//find pivot using median of 3 strategy
		int center = low + (high-low)/2;
		if(data[low] > data[center])
			swap(data,low,center);
		if(data[low] > data[high])
			swap(data,low,high);
		if(data[center] > data[high])
			swap(data,center,high);
		//pivot will be placed at high-1
		swap(data,center,high-1);
		Integer pivot = data[high-1];
		
		int left = low;
		int right = high-1;
		while(true){
			while(data[++left] < pivot){};
			while(data[--right] > pivot){};
			if(left < right)
				swap(data,left,right);
			else
				break;
		}

		//put the pivot(high-1) in its correct place
		swap(data,left,high-1); 
		return left;
	}
	private void swap(Integer[] data, int from, int to){
		Integer temp = data[from];
		data[from] = data[to];
		data[to] = temp;
	}
	private void insertionSort(Integer[] data,int low, int high){
		Integer currentValue;
		int j;
		//high is valid index
		for(int i=low+1; i<=high; i++){
			currentValue = data[i];
			for(j=i-1; (j >= 0) && (currentValue < data[j]); j--)
				data[j+1] = data[j];
			data[j+1] = currentValue;
		}
	}
	public static void main(String args[]){
		QuickSort qs = new QuickSort();
		Integer[] data = new Integer[50];
		for(int i=0; i<50; i++)
			data[i] = 50-i;

		qs.sort(data);
		for(int i=0; i<50; i++)
			System.out.print(data[i] + " ");
	}
}
