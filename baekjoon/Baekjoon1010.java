package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1010 {
	public static int[][] dp = new int[30][30];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			sb.append(combi(n, r)).append('\n');
		}
		
		System.out.println(sb);
	}

	public static int combi(int n, int r) {
		if (dp[n][r] > 0)
			return dp[n][r];
		if (n == r || r == 0)
			return dp[n][r] = 1;
		return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
	}
}
