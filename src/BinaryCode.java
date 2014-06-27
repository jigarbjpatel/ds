public class BinaryCode{
	public static void main(String[] args){
		BinaryCode bc = new BinaryCode();
		for(String s : bc.decode("22111")){
			System.out.println(s);
		};
		
	}
	/**
	 * Decrypts the encrypted string using rules as below
	 * Each char in string is a sum of its previous, current and next position.
	 * The decoded string is a binary string so it can contain only 1 or 0
	 * 
	 * @param encrypted
	 * @return 2 strings - one starting with 0 and another with 1. It it does not exist then return "NONE"
	 */
	public String[] decode(String encrypted){
		String[] res = new String[2];
		if(encrypted.length() == 0)
			return res;
		if(encrypted.length() == 1 ){
			res[0] = encrypted.charAt(0) == '0' ? "0" : "NONE";
			res[1] = encrypted.charAt(0) == '1' ? "1" : "NONE";
			return res;
		}
		//Case 1 - Assume first digit is 0
		int[] decrypted = new int[encrypted.length()];
		decrypted[0] = 0;
		res[0] = helper(decrypted,encrypted);
		//Case 2 - Assume first digit is 1
		decrypted = new int[encrypted.length()];
		decrypted[0] = 1;
		res[1] = helper(decrypted,encrypted);

		return res;
	}
	public String helper(int[] decrypted,String encrypted){
		int i = 1;
		decrypted[i] = (encrypted.charAt(i-1) - '0')- decrypted[i-1];
		
		if(!(decrypted[i] == 0 || decrypted[i] == 1))
			return "NONE";			

		i++;
		while(i < encrypted.length()){
			decrypted[i] =(encrypted.charAt(i-1)- '0') - decrypted[i-1] - decrypted[i-2];
			if(!(decrypted[i] == 0 || decrypted[i] == 1)){
				return "NONE";
			}
			i++;
		}
		if(decrypted[i-1] + decrypted[i-2] != encrypted.charAt(i-1) - '0'){
			return "NONE";
		}
		StringBuilder sb = new StringBuilder();
		for(int j=0; j<decrypted.length; j++){
			sb.append(String.valueOf(decrypted[j]));
		}
		return sb.toString();
	}
}

