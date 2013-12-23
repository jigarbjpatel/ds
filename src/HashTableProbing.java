package edu.cmu.jjpatel;

import java.io.*;

public class HashTableProbing{

	private static class Node{
		private Integer key;
		private String value;
		public Node(Integer key, String value){
			this.key = key;
			this.value = value;
		}
	}

	Node[] buckets;
	int totalBuckets;
	public HashTableProbing(){
		totalBuckets = 10;
		buckets = new Node[totalBuckets];	
	}
	private int getHashCode(Integer key){
		return key.hashCode() % buckets.length;
	}
	public void put(Integer key, String value){
		int bucketIndex = getHashCode(key);
		if(buckets[bucketIndex] != null){
			for(int i = bucketIndex+1; i < bucketIndex + totalBuckets-1; i++){
				if(buckets[i % totalBuckets] == null){
					buckets[i % totalBuckets] = new Node(key,value);
					return;
				}
			}
		}else{
			buckets[bucketIndex] = new Node(key,value);
			return;
		}
		//TODO: Need to resize the buckets array
	}
	public String get(Integer key){
		int bucketIndex = getHashCode(key);
		for(int i=bucketIndex; i < bucketIndex + totalBuckets; i++){
			i = i % totalBuckets;
			if(buckets[i] != null && buckets[i].key == key)
				return buckets[i].value;				
		}
		return "Element not found";
	}
	public void remove(Integer key){
		int bucketIndex = getHashCode(key);
		if(buckets[bucketIndex] != null){
			for(int i=bucketIndex; i < bucketIndex + totalBuckets; i++){
				i = i % totalBuckets;
				if(buckets[i] != null && buckets[i].key == key){
					buckets[i] = null;
					rehash(i+1); // rehash all the buckets from next bucket till u find null
					break;
				}
			}
		}
	}
	private void rehash(Integer i){
		//rehash till there is null value in bucket
		int j=0;
		while(buckets[i] != null || j == totalBuckets){
			Node n = buckets[i];
			buckets[i] = null;
			put(n.key, n.value);
			i = (i+1) % totalBuckets;
			j++;	
		}
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<buckets.length; i++){
			if(buckets[i] != null)
				sb.append(i + " " +buckets[i].key + " " + buckets[i].value + "\n");
		}
		return sb.toString();
	}
	public static void main(String args[]){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String[] tokens;
		HashTableProbing h = new HashTableProbing();
		try{
		while((s = in.readLine()) != null && s.length() != 0){
			tokens = s.split(" ");
			h.put(Integer.parseInt(tokens[0]), tokens[1]);
		}
		}catch(IOException ex){
			System.out.println(ex);
		}
		System.out.println(h.get(1));
		System.out.println("Before Deleting 3rd item");
		System.out.println(h);
		h.remove(3);
		System.out.println("After deleting 3rd item");
		System.out.println(h);
	}
}
