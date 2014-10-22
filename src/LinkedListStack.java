package edu.cmu.jjpatel;

public class LinkedListStack{
	private static class Node{
		Integer data;
		Node next;
		public Node(Integer value, Node next){
			this.data = value;
			this.next = next;
		}
	}
	private Node top;

	public LinkedListStack(){
		top = null;
	}
	public void push(Integer value){
		//Note carefully--- we are making new node point to existing top 
		Node n = new Node(value,top);
		top = n;
	}
	public Integer pop(){
		if(top != null){
			Integer res = top.data;
			top = top.next;
			return res;
		}else{
			throw new RuntimeException("Stack empty");
		}
	}
	public Integer peek(){
		if(top != null)
			return top.data;
		else
			throw new RuntimeException("Stack Empty");
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node n = top;
		while(n!=null){
			sb.append(n.data + " ");
			n = n.next;
		}
		return sb.toString();
	}
	public static void main(String args[]){
		LinkedListStack s = new LinkedListStack();
		s.push(10);
		for(int i=0;i<5;i++)
			s.push(i);
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s.peek());
		System.out.println(s);
	}

}
