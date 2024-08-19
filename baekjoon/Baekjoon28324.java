package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon28324 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		long result = 1;
		long pre = 1;
		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] > pre) {
				pre++;
			} else if (arr[i] < pre) {
				pre = arr[i];
			}
			result += pre;
		}

		System.out.println(result);
	}
}
