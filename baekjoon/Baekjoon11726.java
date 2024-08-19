// 1차 시도 : 틀림. 사유 : square(n - 1) + square(n - 2) % 10007; <- 이렇게 써서 재귀보다 나눗셈을 먼저해버렸음ㅎ;
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon11726 {
	public static long[] DP = new long[1001];

	public static long square(int n) {
		if (DP[n] == -1) {
			return DP[n] = (square(n - 1) + square(n - 2)) % 10007;
		}
		return DP[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		Arrays.fill(DP, -1);
		
		DP[0] = 0;
		DP[1] = 1;
		DP[2] = 2;

		System.out.println(square(N));
	}
}
