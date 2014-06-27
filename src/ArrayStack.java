package edu.cmu.jjpatel;
public class ArrayStack{
	private	Integer[] values;
	private int top;
	private static final int DEFAULT_CAPACITY = 10;
	public ArrayStack(){
		values= new Integer[DEFAULT_CAPACITY];
		top = -1;
	}
	public void push(Integer i){
		if(top == values.length-1){
			throw new RuntimeException("Stack Overflow");
		}
		top++;
		values[top] = i;
	}
	public Integer pop(){
		if(top != -1){
			Integer result = values[top];
			values[top] = null; //required only for reference types not for primitive ones
			top--;
			return result;
		}else{
			throw new RuntimeException("Stack is Empty");
		}
	}
	public Integer peek(){
		if(top != -1)
			return values[top];

		else
			throw new RuntimeException("Stack is empty");
	}
	public static void main(String args[]){
		try{
			ArrayStack as = new ArrayStack();
		
			for(int i=0;i<5;i++)
				as.push(i);
			System.out.println(as.pop());
			System.out.println(as.peek());
			for(int i=4;i<10;i++)
				as.push(i);
			System.out.println(as.peek());
			as.push(11);
		}catch(RuntimeException ex){
			System.out.println(ex);
		}
	}

}
