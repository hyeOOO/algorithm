package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1486 {
	static int N, B;
	static int[] arr;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			makeTop(0, 0);
			System.out.printf("#%d %d\n", t, min - B);
		}
	}

	public static void makeTop(int depth, int height) {
		if (min < height)
			return;
		if (depth == N) {
			if (B <= height)
				min = Math.min(min, height);
			return;
		}

		makeTop(depth + 1, height + arr[depth]);
		makeTop(depth + 1, height);
	}
}
