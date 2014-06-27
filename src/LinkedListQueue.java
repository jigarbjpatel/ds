package edu.cmu.jjpatel;
import java.util.*;
public class LinkedListQueue{

	private static class Node{
		Integer data;
		Node next;
		public Node(Integer value, Node n){
			this.data = value;
			this.next = n;
		}
	}

	private Node first;
	private Node last;
	private int size;

	public LinkedListQueue(){
		first = last = null;		
		size = 0;
	}

	public void enqueue(Integer value){
		Node n = new Node(value,null);
		if(size == 0){
			first = last = n;
		}else{
			last.next = n;
			last = n;
		}
		size++;
	}

	public Integer dequeue(){
		if(first != null){
			Integer result = first.data;
			first = first.next;
			size--;
			if(size == 0)
				last = null; //Make last also null as it will be otherwise pointing to the dequeued element
			return result;
		}else{
			throw new NoSuchElementException("Queue is Empty");
		}
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node temp = first;
		while(temp != null){
			sb.append(temp.data + " ");
			temp = temp.next;
		}
		return sb.toString();
	}

	public static void main(String args[]){
		LinkedListQueue q = new LinkedListQueue();
		for(int i=0 ;i<10; i++)
			q.enqueue(i);

		System.out.println(q);
		System.out.println(q.dequeue());
		System.out.println(q);
		for(int i=0;i<5;i++)
			q.dequeue();

		System.out.println(q);
	}

}
