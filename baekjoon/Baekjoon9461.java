// 1차 시도 : 실패. 왜지? 범위 문제였음.
// 100의 경우 9000억 가까이 되기 때문에 int형 범위를 넘어가기 때문임..
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon9461 {
	public static long[] P = new long[101];

	public static long triangle(int n) {
		if (P[n] == -1) {
			P[n] = triangle(n - 2) + triangle(n - 3);
		}
		return P[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		Arrays.fill(P, -1);

		P[0] = 0;
		P[1] = 1;
		P[2] = 1;
		P[3] = 1;

		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(triangle(num)).append("\n");
		}

		System.out.println(sb);
	}
}
