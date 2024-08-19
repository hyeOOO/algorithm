package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1107_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		boolean[] broken = new boolean[10];

		if (M != 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				int brokenBtn = Integer.parseInt(st.nextToken());
				broken[brokenBtn] = true;
			}
		}
		
		// +1 혹은 -1로만 목표채널 카운팅
		int result = Math.abs(N - 100);

		for (int i = 0; i <= 999999; i++) {
			String str = Integer.toString(i);
			int len = str.length();

			boolean isBroken = false;

			for (int j = 0; j < len; j++) {
				if (broken[str.charAt(j) - '0']) {
					isBroken = true;
					break;
				}
			}

			if (!isBroken) {
				int min = Math.abs(N - i) + len;
				result = Math.min(result, min);
			}
		}

		System.out.println(result);
	}
}
