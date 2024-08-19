// 문제 이해 실패!
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11053 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int max = Integer.MIN_VALUE;
		int index = -1;
		for (int i = 0; i < N; i++) {
			if (i == N - 1)
				break;
			int next = i + 1;
			int diff = arr[next] - arr[i];

			if (diff > max) {
				max = diff;
				index = i;
			}
		}

		System.out.println(index);
	}
}
