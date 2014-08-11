public class ReverseString{

	public static void main(String args[]){
		String s = args[0];
		System.out.println(reverseString(s));
	}
	public static String reverseString(String s){
		int startIndex = 0;
		int endIndex = s.length()-1; //for C style string we have to iterate till we find null
		char[] chars = s.toCharArray();
		while(startIndex < endIndex){
			char tmp = chars[startIndex];
			chars[startIndex] = chars[endIndex];
			chars[endIndex] = tmp;
			startIndex++;
			endIndex--;
		}
		return new String(chars);
	}

}
