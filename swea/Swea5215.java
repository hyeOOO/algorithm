package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea5215 {
	static int N, L;
	static int[] taste;
	static int[] cal;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			taste = new int[N];
			cal = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			getSubSet(0, 0, 0);
		}
	}

	public static void getSubSet(int cnt, int calSum, int tasteSum) {
		if (calSum > L)
			return;
		if (cnt == N) {
			if (calSum <= L)
				max = Math.max(max, tasteSum);
			return;
		}
		getSubSet(cnt + 1, calSum + cal[cnt], tasteSum + taste[cnt]);
		getSubSet(cnt + 1, calSum, tasteSum);
	}
}
