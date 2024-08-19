package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1003 {
	public static long[][] F = new long[41][2];

	public static long[] fibo(int n) {
		if(F[n][0] == -1 || F[n][1] ==-1) {
			F[n][0] = fibo(n-1)[0]+fibo(n-2)[0];
			F[n][1] = fibo(n-1)[1]+fibo(n-2)[1];
		}
		return F[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<41; i++) {			
			Arrays.fill(F[i], -1);
		}
		
		F[0][0] = 1;
		F[0][1] = 0;
		F[1][0] = 0;
		F[1][1] = 1;
		
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			fibo(N);
			sb.append(F[N][0]).append(" ").append(F[N][1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
