package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon11727 {
	public static long[] DP = new long[1001];

	public static long square(int n) {
		if (DP[n] == -1) {
			return DP[n] = (square(n - 1) + square(n - 2) * 2) % 10007;
		}
		return DP[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Arrays.fill(DP, -1);

		DP[0] = 0;
		DP[1] = 1;
		DP[2] = 3;

		System.out.println(square(N));
	}
}
