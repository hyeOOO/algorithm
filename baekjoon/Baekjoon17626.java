// 규칙이 안보여서 인터넷 보고 품... 아 저 제곱근 구하는 부분 몇 번 본 거 같은데 뭐 쓰지도 못하겠음
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon17626 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n + 1];

		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			// 현재 구할 값의 개수는 보통 전 값의 +1이니 전 값을 그대로 우선 가져온다.
			dp[i] = dp[i - 1];
			for (int j = 1; j * j <= i; j++) {
				//현재 들어가있는 값과, i - j * j의 값과 비교해서 작은 값을 다시 저장
				dp[i] = Math.min(dp[i], dp[i - (j * j)]);
			}
			//최솟값이 저장되었을테니 +1을 해서 마무리 해준다.
			dp[i]++;
		}

		System.out.println(dp[n]);
	}
}
