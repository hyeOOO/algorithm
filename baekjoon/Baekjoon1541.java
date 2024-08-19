package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1541 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int result = Integer.MIN_VALUE;
		StringTokenizer subtraction = new StringTokenizer(br.readLine(),"-");
		
		while(subtraction.hasMoreTokens()) {
			int temp = 0;
			
			StringTokenizer addition = new StringTokenizer(subtraction.nextToken(), "+");
			
			while(addition.hasMoreTokens()) {
				temp += Integer.parseInt(addition.nextToken());
			}
			
			if(result == Integer.MIN_VALUE) {
				result = temp;
			}else {
				result-=temp;
			}
		}
		System.out.println(result);
	}
}
