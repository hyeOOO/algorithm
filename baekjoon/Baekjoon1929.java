package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1929 {
	public static boolean isPrime(int n) {
		if(n==1) return false;
		for(int i=2; i*i<=n; i++) {
			if(n%i==0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		for(int i=m; i<=n; i++) {
			if(isPrime(i)) sb.append(i).append("\n");
		}
		
		System.out.println(sb);
	}
}
