package edu.cmu.jjpatel;

public class MergeSort{

	public void sort(Integer[] data){
		Integer[] aux = new Integer[data.length];
		sort(data,0,data.length-1,aux);
	}
	private void sort(Integer[] data, int low, int high, Integer[] aux){
		if(low >= high)
			return;
		int mid = low + (high-low)/2;

		sort(data,low,mid,aux);
		sort(data,mid+1,high,aux);
		merge(data,low,mid,high,aux);
	}
	private void merge(Integer[] data, int low, int mid, int high, Integer[] aux){
		int left = low;
		int right = mid+1;
		int rightEnd = high;
		int auxPos = 0;
		while(left <= mid && right <= rightEnd){
			if(data[left] <= data[right]){
				aux[auxPos] = data[left];
				left++;
			}else{
				aux[auxPos] = data[right];
				right++;
			}
			auxPos++;
		}
		while(left <= mid){
			aux[auxPos] = data[left];
			auxPos++; left++;
		}
		while(right <= rightEnd){
			aux[auxPos] = data[right];
			auxPos++; right++;
		}
		for(int i=0; i<auxPos; i++)
			data[low+i] = aux[i];

	}
	public static void main(String args[]){
		Integer[] data = new Integer[50];
		for(int i=0; i<50; i++)
			data[i] = 50-i*2;
		MergeSort ms = new MergeSort();
		ms.sort(data);
		for(int i=0; i<50; i++)
			System.out.print(data[i] + " ");
	}
}
