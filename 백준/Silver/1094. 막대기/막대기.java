import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		String binaryNum = Integer.toBinaryString(X);
		
		int count = 0;
		for(char c : binaryNum.toCharArray()) {
			if(c=='1') count++;
		}
		
		System.out.println(count);
	}
}
