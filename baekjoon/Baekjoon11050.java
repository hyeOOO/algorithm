//DP사용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11050 {
	static int[][] F;

	private int fact(int n, int k) {
		if (k == 0 || n == k)
			return 1;
		return F[n][k] = fact(n - 1, k) + fact(n - 1, k - 1);
	}

	public static void main(String[] args) throws IOException {
		Baekjoon11050 M = new Baekjoon11050();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		F = new int[n+1][k+1];
		
		System.out.println(M.fact(n,k));
	}
}
