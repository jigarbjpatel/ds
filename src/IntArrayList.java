package edu.cmu.jjpatel;
import java.util.Arrays;
import java.util.Iterator;

public class IntArrayList implements Iterable<Integer>{
	private Integer[] arr;
	private int actualSize=0;
	public IntArrayList(){
	    arr = new Integer[1];
	}
	public int size(){
	    return actualSize;
	}
	private void resize(int newSize){
	    arr = Arrays.copyOf(arr, newSize);
	}
	public void add(int newInt){
	    if(actualSize == arr.length){
		resize(arr.length*2);
	    }
	    arr[actualSize++] = newInt;
	}
	public void remove(int index){
	    if(index >= arr.length)
		throw new ArrayIndexOutOfBoundsException();
	    while(index < actualSize-1){
	    	arr[index] = arr[index+1];
		index++;
	    }
	    arr[index] = null;
	    actualSize--;
	    if(actualSize < arr.length/2){
	    	resize(arr.length/2);
	    }
	}	
	public int get(int index){
	    if(index >= arr.length)
		throw new ArrayIndexOutOfBoundsException();
	    return arr[index];
	}
	public boolean contains(int item){
	//This is just for using Arrays sort and search otherwise sequential search would be faster if the array is not sorted
		Arrays.sort(arr,0,actualSize);
		int index = java.util.Arrays.binarySearch(arr,0,actualSize,item);
		return index < 0 ? false : true;
	}
	@Override
	public Iterator<Integer> iterator(){
		
		Iterator<Integer> it = new Iterator<Integer>(){
			private int currentPosition = 0;
			@Override
			public boolean hasNext(){
				return currentPosition < actualSize;
			}
			@Override
			public Integer next(){
				if(currentPosition < actualSize)
					return arr[currentPosition++];
				else
					throw new java.util.NoSuchElementException();
			}
			@Override
			public void remove(){
				//TODO:
			}
		};
		return it;
	}

	public static void main(String args[]){
	    IntArrayList al = new IntArrayList();
	    al.add(3);
	    al.add(5);
	    al.add(8);
	    al.add(2);
	    System.out.println(al.size());
	    System.out.println(al.get(1));
	    al.remove(0);
	    System.out.println(al.get(0));
	    System.out.println(al.size());
	    System.out.println(al.contains(2));
	    for(Integer i : al){
		System.out.println(i);
	    }
	}
}
