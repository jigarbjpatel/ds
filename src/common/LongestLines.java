import java.util.ArrayList;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;
public class LongestLines{
	public static void main(String args[]){
		String filePath = args[0];
		BufferedReader br = null;
		ArrayList<String> lines = new ArrayList<String>();
		try{
			br = new BufferedReader(new FileReader(new File(filePath)));
			String line = "";
			while((line = br.readLine()) != null){
				if(!line.equals(""))
					lines.add(line);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		Collections.sort(lines,new LineComparator());
		System.out.println(lines);
	}
	private static class LineComparator implements Comparator<String>{
		@Override
		public int compare(String l1, String l2){
			return l1.length()-l2.length();
		}
	}
}
