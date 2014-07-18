package common;
public class Basics{
	static Integer s;
	final Integer x;
	public static void main(String args[]){
		Basics b = new Basics(8);
		Basics b1 = new Basics(10);
		System.out.println(b.x + " " + b1.x);
		System.out.println(System.currentTimeMillis());
		
	}
	public Basics(int i){
		x=i;
	}
	static{
		s = 1;
	}
	@Override
	public int hashCode(){
		return x.hashCode();	
	}
	@Override
	public boolean equals(Object other){
		if(other == this)
			return true;
		if(other == null)
			return false;
		Basics o = (Basics)other;
		return this.x == o.x;
	}
}
