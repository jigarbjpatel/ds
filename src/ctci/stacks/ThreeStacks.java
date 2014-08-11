import java.util.LinkedList;

public class ThreeStacks{

	public static void main(String args[]){
		ThreeStacks s = new ThreeStacks();
		s.push(1,"test");
		s.push(2,"2nd");
		s.push(3,"3rd");
		s.push(3,"3rd again");
		s.push(2,"2nd again");
		System.out.println(s.pop(3));
		s.pop(1);
		s.push(1,"first again");
		System.out.println(s);
	}

	private class StackNode{
		public int prev;
		public String data;
		public StackNode(String data, int prev){
			this.data = data;
			this.prev = prev;
		}
	}

	private StackNode[] buffer = new StackNode[5];
	private int[] topIndices = {-1,-1,-1};
	private int nextFreeIndex = 0;
//If space is concern then traverse the buffer to find a null node each time while pushing new element
	private LinkedList<Integer> freeNodes = new LinkedList<Integer>();

	public void push(int stackNumber, String data){
		int prev = topIndices[stackNumber-1];
		if(nextFreeIndex != buffer.length){
			buffer[nextFreeIndex] = new StackNode(data,prev);
			topIndices[stackNumber-1] = nextFreeIndex;
			nextFreeIndex++;
		}else if(freeNodes.size() != 0){
			Integer freeIndex = freeNodes.removeFirst();
			if(freeIndex != null){
				buffer[freeIndex] = new StackNode(data,prev);
				topIndices[stackNumber-1] = freeIndex;
			}
		}else{
			throw new RuntimeException("Stack Overflow");	
		}
	}

	public String pop(int stackNumber){
		int index = topIndices[stackNumber-1];
		if(index != -1){
			StackNode n = buffer[index];
			buffer[index] = null;
			freeNodes.add(index);
			topIndices[stackNumber-1] = n.prev;
			return n.data;
		}else{
			throw new RuntimeException("Stack Empty");
		}
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<topIndices.length; i++){
			sb.append("\nStack " + i + " ");
			int index = topIndices[i];
			while(index != -1){
				sb.append(buffer[index].data + " ");
				index = buffer[index].prev;
			}
		}
		return sb.toString();
	}

}
