package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2748 {
	public static long[] F;

	public static long fibo(int n) {
		if (n == 0)
			return F[0];
		if (n == 1)
			return F[1];

		if (F[n] != -1)
			return F[n];
		return F[n] = fibo(n - 1) + fibo(n - 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		F = new long[n + 1];
		Arrays.fill(F, -1);

		F[0] = 0;
		F[1] = 1;

		System.out.println(fibo(n));
	}
}
