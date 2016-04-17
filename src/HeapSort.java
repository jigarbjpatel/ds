package edu.cmu.jjpatel;

public class HeapSort{
	//O(nlogn) time and O(n) space for heap
	public static void sortiUsingMinHeap(Integer[] data){
		MinHeap minh = new MinHeap();
		for(int i=0; i<data.length; i++)
			minh.insert(data[i]); //O(log n)

		for(int i=0; i<data.length; i++)
			System.out.print(minh.deleteMin());
	}
	//O(nlogn) but no extra space
	public  void sort(Integer[] data){
		buildHeap(data); //O(n)
		
		int N = data.length;
		while(N > 0){
			swap(data,0,N-1);
			N--;
			percolateDown(data,0,N); //O(logn)
		}
	}
	//We only precolate down half of elements. Why?
	private void buildHeap(Integer[] data){
		int N = data.length;
		for(int i=N/2; i>=0; i--)
			percolateDown(data,i,N);
	}
	private void percolateDown(Integer[] data, int index, int N){
		int leftChildIndex = 2*index+1;
		while(leftChildIndex < N){
			int childToCompareWith = leftChildIndex;
			if(leftChildIndex < N-1 && data[leftChildIndex] < data[leftChildIndex+1]){
				childToCompareWith = leftChildIndex+1;
			}
			if(data[index] < data[childToCompareWith]){
				swap(data,index,childToCompareWith);
				index = childToCompareWith;
				leftChildIndex = 2*index+1;
			}
			else{
				break;
			}
		}
	}
	private void swap(Integer[] data, int from, int to){
		Integer temp = data[from];
		data[from] = data[to];
		data[to] = temp;
	}	
	public static void main(String args[]){
		Integer[] data = new Integer[100];
		for(int i=0; i<100; i++)
			data[i] = 100-i;
		data[2] = 511;
		data[5] = 501;
		data[8] = 341;
		new HeapSort().sort(data);
		for(int i=0; i<100; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}
}
