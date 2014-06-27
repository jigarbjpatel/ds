
import java.util.*;

public class Lottery{
	public static void main(String args[]){
		Lottery l = new Lottery();
		String[] rules = {"PICK ANY TWO: 10 2 F F"
				,"PICK TWO IN ORDER: 10 2 T F"
				,"PICK TWO DIFFERENT: 10 2 F T"
				,"PICK TWO LIMITED: 10 2 T T"};
		String[] res = l.sortByOdds(rules );
		for(String s : res)
			System.out.println(s);
	}
	public String[] sortByOdds(String[] rules){
		TreeMap<Long,TreeSet<String>> res = new TreeMap<Long,TreeSet<String>>();
		String[] temp = null;
		Long odd;
		TreeSet<String> t = new TreeSet<String>();
		for(String rule : rules){
			temp = rule.split(":");
			odd = getOdds(temp[1].trim());
			if(res.containsKey(odd)){
				res.get(odd).add(temp[0]);
			}else{
				t = new TreeSet<String>();
				t.add(temp[0]);
				res.put(odd, t);
			}
		} 
		String[] odds = new String[rules.length];
		int i=0;
		for(Map.Entry<Long,TreeSet<String>> e : res.entrySet()){
			for(String s : e.getValue()){
				odds[i] = s;
				i++;
			}
		}
		return odds;
	}

	public Long getOdds(String secondPart){
		String[] temp = secondPart.split(" ");
		Long odd = 0L;
		Integer n = Integer.valueOf(temp[0]);
		Integer r = Integer.valueOf(temp[1]);
		if(temp[2].equals("T")){
			if(temp[3].equals("T"))
				odd = getCombination(n,r,false);
			else
				odd = getCombination(n,r,true);
		}else{
			if(temp[3].equals("T"))
				odd = getPermutation(n,r,false);
			else
				odd = getPermutation(n,r,true);
		}
		return odd;
	}
	/**
	 * If repeat the returns Power(n,r)
	 * else returns P(n,r) = n! / (n-r)!
	 * @param n
	 * @param r
	 * @param repeat
	 * @return
	 */
	private Long getPermutation(Integer n, Integer r, boolean repeat) {
		Long res = 1L;
		if(repeat){
			res = (long) Math.pow(n,r);
		}else{
			for(int i=n; i>(n-r); i--)
				res *= i;
		}
		return res;
	}

	/**
	 * If repeat then formulae is C(n,r) = C(n+r-1, r)
	 * else the formulae is C(n,r) = n!/r!*(n-r)!
	 * @param n
	 * @param r
	 * @param repeat
	 * @return
	 */
	private Long getCombination(int n, int r, boolean repeat){
		Long res = 1L;
		if(repeat){
			res = getCombination(n+r-1, r, false);
		}else{
			//n! / (n-r)!
			for(int i=n; i>(n-r); i--)
				res *= i;
			//Divide by r!
			for(int i=r; i>0; i--)
				res /= i;
		}
		return res;
	}

}
