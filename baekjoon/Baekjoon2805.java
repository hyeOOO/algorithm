// 왜틀림?
// len은 int의 범위를 벗어날 수 있으므로 long으로 선언해야함..^^
// upperBound.... 특징 배워둘 것
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] tree = new int[N];

		st = new StringTokenizer(br.readLine(), " ");

		int start = 0;
		int end = 0;

		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if (end < tree[i])
				end = tree[i];
		}

		while (end > start) {
			int mid = (start + end) / 2;

			long len = 0; // 이거 int면 틀림;

			for (int i = 0; i < N; i++) {
				if (tree[i] - mid > 0)
					len += (tree[i] - mid);
			}

			if (len < M) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(start-1);
	}
}
