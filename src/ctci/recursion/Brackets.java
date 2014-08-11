import java.util.*;
import java.util.Scanner;
public class Brackets{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Brackets b = new Brackets();
		System.out.println(b.printBrackets(n));
	}
	public Set<String> printBrackets(int n){
		//Set because there can be duplicates with previous entries
		Set<String> sol = new LinkedHashSet<String>();
		if(n<0)
			return null;
		if(n==1){
			sol.add("()");
			return sol;
		}
		Set<String> previousSol = printBrackets(n-1);
		for(String s : previousSol){
			String addingAtBack = s+"()";
			sol.add(addingAtBack);
			sol.add("(" + s + ")");
			String addingAtFront = "()"+s;
			if(!addingAtFront.equals(addingAtBack)) //just to avoid unnecessary checks by Set
				sol.add(addingAtFront);
		}
		
		return sol;
	}

}
