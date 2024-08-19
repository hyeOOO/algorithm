package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2033 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		int num = Integer.parseInt(N);
		
		for(int i=1; i<N.length(); i++) {
			num = (int) ((int)Math.round(num/Math.pow(10, i))*Math.pow(10, i));
		}
		
		System.out.println(num);
	}
}
