import java.util.Stack;

public class SortOnStack{

	public static void main(String args[]){
		SortOnStack s = new SortOnStack();
		for(int i=0; i<5; i++)
			s.add((int)(Math.random() * 5));
		System.out.println(s);
		s.sort();

		System.out.println(s);
	}
	Stack<Integer> s = new Stack<Integer>();

	public void add(Integer value){
		s.push(value);
	}

	public void sort(){
		sort(s);
	}
	private void sort(Stack<Integer> s){
		//pop the top elem
		//sort the rest of stack
		//insert the top back to stack
		if(!s.isEmpty()){
			Integer top = s.pop();
			sort(s);
			insert(top,s);
		}
	}
	private void insert(Integer value, Stack<Integer> s){
		if(s.isEmpty())
			s.push(value);
		else{
			if(s.peek() >= value)
				s.push(value);
			else{
				Integer temp = s.pop();
				insert(value,s);
				s.push(temp);
			}		
		}
	}

	public String toString(){
		StringBuilder sb= new StringBuilder();
		for(Integer i : s)
			sb.append(i + " ");
		return sb.toString();
	}


}
