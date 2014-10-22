
import java.util.*;

public class LinkedList implements Iterable<Integer>{
	private class Node{
		Integer data;
		Node next;
		public Node(Integer value, Node next){
			this.data = value;
			this.next = next;
		}
	}
	private Node head;
	public LinkedList(){
		head = null;
	}
	public void add(Integer value){
		//adds at the end of list
		Node newNode = new Node(value,null);
		if(head == null)
			head = newNode;
		else{
			Node temp = head;
			while(temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
	}
	public boolean contains(Integer value){
		if(head == null)
			return false;
		Node temp = head;
		while(temp != null){
			if(temp.data == value)
				return true;
			temp = temp.next;
		}
		return false;		
	}
	public void remove(Integer value){
		if(head != null){
			if(head.data == value)
				head = head.next;
			else{
				Node previousNode = head;
				Node currentNode = head.next;
				while(currentNode != null){
					if(currentNode.data == value){
						previousNode.next = currentNode.next;
						break;
					}
					previousNode = currentNode;
					currentNode = currentNode.next;
				}
			}
		}
	}
	public Iterator<Integer> iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator<Integer>{
		Node currentNode;
		public LinkedListIterator(){
			currentNode = head;
		}
		public boolean hasNext(){
			return currentNode != null ? true : false;
		}
		public Integer next(){
			if(currentNode != null){
				Integer result =  currentNode.data;
				currentNode = currentNode.next;
				return result;
			}else{
				throw new NoSuchElementException();
			}
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	public String toString(){
		StringBuffer result = new StringBuffer();
		for(Integer i : this){
			result.append(i + "  ");
		}
		return result.toString();
	}
	public void reverseInPlace(){
		reverseRecursively(head);
	}
	public void reverseRecursively(Node currNode){
		if(currNode == null)
			return; //empty list case
		if(currNode.next == null){
			head = currNode; // this is tail node so let head point to it
			return;
		}
		reverseRecursively(currNode.next);
		currNode.next.next = currNode; //last node's next = second last node and so on...
		currNode.next = null; //second last node's next = null and so on it will make first node's next also null
		
	}
	public void reverseIteratively(){
		if(head != null){
			Node prev = null;
			Node curr = head;
			Node next = null;
			while(curr != null){
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			head = prev;			
		}
	}
	public static void main(String args[]){
		LinkedList intList  = new LinkedList();
		intList.add(5);
		intList.add(19);
		intList.add(20);
		System.out.println(intList.contains(20));
		intList.remove(19);
		System.out.println(intList.contains(19));
		intList.remove(1);
		intList.add(12);
		intList.add(11);
		for(Integer i : intList)
			System.out.print(i + " ");
		intList.reverseInPlace();
		System.out.println();
		Iterator itr = intList.iterator();
		while(itr.hasNext())
			System.out.print(itr.next() + " ");
		System.out.println();
		intList.reverseIteratively();
		for(Integer i : intList)
			System.out.print(i + " ");
		//LinkedList reversedlist = intList.reverse() //Use addFirst logic and return new list OR recursively reverse OR iterate
		//LinkedList deepCopyList = intList.deepCopy() //Use 2 pointers - 1 for old list and 1 for new list and copy only data
	}
}
