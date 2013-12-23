package edu.cmu.jjpatel;
import java.util.*;
public class ArrayQueue{

	private Integer[] A;
	private int first;
	private int last;
	private int size;
	public ArrayQueue(){
		A = new Integer[5];
		first = last = size = 0;
	}
	public void enqueue(Integer value){
		if(size == A.length)
			resize(size*2);
	
		A[last] = value; //last points to the next empty space
		last++;
		size++;
		if(last == A.length)
			last = 0; //wrapping the queue
		
	}
	private void resize(int newSize){
		Integer[] temp = new Integer[newSize];
		for(int i=first; i < first+size; i++){
			temp[i - first] = A[i % size];
		}
		A = temp;
		first = 0;
		last = size;
	}
	private Integer dequeue(){
		if(size != 0){
			Integer res = A[first];
			A[first] = null;
			first++;
			if(first == A.length)
				first = 0;
			size--;
			return res;
		}else{
			throw new NoSuchElementException("Queue Empty");
		}
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=first; i<first+size; i++)
			sb.append(A[i % A.length] + " ");
		return sb.toString();
	}
	public static void main(String args[]){
		ArrayQueue q = new ArrayQueue();
		for(int i=0; i<10; i++)
			q.enqueue(i);
		System.out.println(q);
		int a = q.dequeue();
		System.out.println(a);
		System.out.println(q);
	}
}
