import java.util.*;

public class TowerOfHanoi{

	public static void main(String[] args){
		TowerOfHanoi[] towers = new TowerOfHanoi[3];
		for(int i=0; i<3; i++)
			towers[i] = new TowerOfHanoi();
		Scanner in = new Scanner(System.in);
		int numberOfDisks = in.nextInt();
		for(int i=0; i<numberOfDisks; i++)
			towers[0].add(numberOfDisks-i);
		for(int i=0; i<3; i++)
			System.out.println("Tower " + i + " " +towers[i]);

		moveDisks(numberOfDisks,towers[0],towers[1],towers[2]);
		for(int i=0; i<3; i++)
			System.out.println("Tower " + i + " " +towers[i]);
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<disks.size();i++)
			sb.append(disks.get(i) +" ");
		return sb.toString();
	}

	private Stack<Integer> disks = new Stack<Integer>();
	public void add(Integer value){
		if(disks.size() == 0 || value < disks.peek())
			disks.push(value);
		else throw new RuntimeException("Not allowed");
	}
	public Integer top(){
		if(disks.size() != 0){
			return disks.pop();
		}else throw new RuntimeException("Stack Empty");
	}
	public static void moveDisks(int numberOfDisks, TowerOfHanoi source, TowerOfHanoi buffer, TowerOfHanoi dest){
		source.move(numberOfDisks,buffer,dest);
	}
	public void move(int n, TowerOfHanoi buffer, TowerOfHanoi dest){
		//move n-1 disks from source to buffer
		//move nth disk from source to destination
		//move n-1 disks from buffer to destination
		if(n > 0){
			this.move(n-1,dest,buffer);
			dest.add(this.top());
			buffer.move(n-1,this,dest);
		}
	}

}
