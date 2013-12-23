package edu.cmu.jjpatel;

import java.io.*;
import java.util.*;
/**
* Hash Map with key of type integer and value of type string
*/
public class HashTableChaining{

	private static class Node{
		private Integer key;
		private String value;
		private Node next;
		public Node(Integer key, String value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	private LinkedList<Node>[] buckets;
	private int totalValues;
	private int bucketSize;
	public HashTableChaining(){
		bucketSize = 10;
		//generic array creation is not allowed so create object array and cast it
		buckets = (LinkedList<Node>[]) new LinkedList[bucketSize];
		totalValues = 0;
	}	
	private int getHashCode(Integer key){
		return key.hashCode() % bucketSize;
	}
	public void put(Integer key, String value){
		int bucketIndex = getHashCode(key);
		if(buckets[bucketIndex] == null){
			//case 1: bucket is empty
			buckets[bucketIndex] = new LinkedList<Node>();
			buckets[bucketIndex].add(new Node(key,value));
			totalValues++;			
		}else{
			Iterator<Node> itr = buckets[bucketIndex].iterator();
			while(itr.hasNext()){
				Node n = itr.next();
				if(n.key == key){//case 2: key found in bucket
					n.value = value;
					break;
				}
			}
			//case 3: key not found in bucket
			buckets[bucketIndex].add(new Node(key,value));
		}
	}
	public String get(Integer key){
		int bucketIndex = getHashCode(key);
		LinkedList<Node> l = buckets[bucketIndex];
		if(l != null){
			Iterator<Node> itr = l.iterator();
			while(itr.hasNext()){
				Node n = itr.next();
				if(n.key == key)
					return n.value;
			}
		}
		return "No such key";
	}
	public void remove(Integer key){
		int bucketIndex = getHashCode(key);
		LinkedList<Node> l = buckets[bucketIndex];
		if(l != null){
			Node n = null;
			Iterator<Node> itr = l.iterator();
			while(itr.hasNext()){
				n = itr.next();
				if(n.key == key)
					break;
			}
			if(n != null){
				l.remove(n);
			}
		}
		
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<buckets.length; i++){
			LinkedList<Node> l = buckets[i];
			if(l != null){
				for(Node n: l){
					sb.append(n.key + " " + n.value + "\n");
				}
			}
		}
		return sb.toString();
	}
	public static void main(String args[]){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String[] tokens;
		HashTableChaining h = new HashTableChaining();
		try{
		while((s = in.readLine()) != null && s.length() != 0){
			tokens = s.split(" ");
			h.put(Integer.parseInt(tokens[0]),tokens[1]); //key, value
		}
		}catch(IOException ex){
			System.err.println(ex);
		}
		System.out.println(h.get(1));
		h.remove(2);	
		System.out.println(h);
	}
}
