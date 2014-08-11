public class Basics implements Comparable<Basics>{

Integer id;
@Override
public int compareTo(Basics other){
	return id.compareTo(other.id);
}
	public static void main(String[] args) throws Exception{
		Integer a =0;
		Byte b=0;
		System.out.println(a.equals(b));
		//System.out.println(m(Integer.parseInt(args[0])));
	}
	static String m(int a) throws Exception{
		try{
			System.out.println("try....");
			if(a/100 == 2) return "return from try";
			if(a/100 == 3) throw new Exception("thrown by try");
			if(a/100 == 4) throw new RuntimeException("thrown by try");
		}catch(RuntimeException x){
			System.out.println("catch");	
			if(a/10%10 == 2) return "returned from catch";
			if(a/10%10 == 3) throw new Exception("thrown by catch");
		}finally{
			System.out.println("finally");
			if(a%10 == 2) return "returned from finally";
			if(a%10 == 3) throw new Exception("thrown by finally");
		}
		return "terminated normally with " + a;

	}
}
