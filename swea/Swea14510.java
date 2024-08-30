package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [swea/14510] 나무 높이
 * 
 * @since 2024.08.29
 */
public class Swea14510 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] trees = new int[N];
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(trees);

			int maxHeight = trees[N - 1];
			long need1 = 0;
			long need2 = 0;

			for (int i = 0; i < N; i++) {
				int need = maxHeight - trees[i];
				need2 += need / 2;
				need1 += need % 2;
			}

			boolean flag = false;
			if (need2 < need1)
				flag = true;
			// need1과 need2의 차이를 최소화
//			while (need1 > need2 + 1) {
//				need1--;
//				need2 += 2;
//			}

			while (need2 > need1 + 1) {
				need2--;
				need1 += 2;
			}

			long result;
			if (need1 == need2) {
				if (flag)
					result = need1 * 2 + 1;
				else result = need2 * 2;
			} else if (need1 > need2) {
				result = need1 * 2 - 1;
			} else {
				result = need2 * 2;
			}

			System.out.printf("#%d %d\n", t, result);
		}
	}
}
