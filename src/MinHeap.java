package edu.cmu.jjpatel;
public class MinHeap{
	private Integer[] heap;
	private int size;
	
	public MinHeap(){
		size = 0;
		heap = new Integer[11]; //0th index wont be used
	}
	public void insert(Integer value){
		int position = ++size;
		//Percolate up the new value by comparing with parent located at i/2 position
		while(position > 1){
			if(value < heap[position/2]){
				heap[position] = heap[position/2];
				position = position/2;
			}else
				break;
		}
		heap[position] = value; //place the value at its correct position
	}
	public Integer deleteMin(){
		Integer min;
		if(size > 0){			
			min = heap[1];
			heap[1] = heap[size];
			size--;
			//percolate Down the root
			int currentPosition = 1;
			int leftChildIndex;
			int rightChildIndex;
			int childToCompareWith;
			while(currentPosition <= size){	
				leftChildIndex = currentPosition * 2;
				rightChildIndex = leftChildIndex + 1;
				if(leftChildIndex > size)
					break;			
				//find which child is smaller
				childToCompareWith = (rightChildIndex <= size && heap[rightChildIndex] < heap[leftChildIndex]) 
								? rightChildIndex : leftChildIndex;
				//Swap if required else break
				if(heap[currentPosition] > heap[childToCompareWith]){
					Integer temp = heap[currentPosition];
					heap[currentPosition] = heap[childToCompareWith];
					heap[childToCompareWith] = temp;
					currentPosition = childToCompareWith;
				}else{
					break;
				}
				
			}
		}else throw new RuntimeException();
		return min;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<=size; i++)
			sb.append(heap[i] + " ");
		return sb.toString();
	}
	public static void main(String args[]){
		MinHeap h = new MinHeap();
		for(int i=10; i>0; i--)
			h.insert(i);

		System.out.println(h);
		for(int i=0; i<10; i++){
			System.out.println(h.deleteMin());
			System.out.println(h);
		}
	}

}
