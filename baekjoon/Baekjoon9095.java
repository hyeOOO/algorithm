// 이거 시도 못함.. 규칙 찾는걸 못해서..
// 규칙 아니까 풀만함
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon9095 {
	public static int[] N = new int[12];

	public static int recur(int n) {
		if (N[n] == -1) {
			return N[n] = recur(n - 1) + recur(n - 2) + recur(n - 3);
		}
		return N[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		Arrays.fill(N, -1);

		N[0] = 0;
		N[1] = 1;
		N[2] = 2;
		N[3] = 4;

		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(recur(num)).append("\n");
		}
		
		System.out.println(sb);
	}
}
