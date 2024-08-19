// 점화식 세우기 실패
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1309 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int MOD = 9901;
		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][3]; // j=0 : 동물 배치 x, j=1 : 1번째 칸에 배치, j=2 : 2번째 칸에 배치
		dp[1][0] = dp[1][1] = dp[1][2] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
		}

		long answer = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
		System.out.println(answer);
	}
}
