package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2512 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int max = 0;
		int min = 0;
		long budgetSum = 0;

		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			budgetSum += budget[i];
			if (budget[i] > max)
				max = budget[i];
		}

		int M = Integer.parseInt(br.readLine());

		if (budgetSum > M) {
			while (min < max) {
				int mid = (min + max) / 2;

				long sum = 0;

				for (int i = 0; i < N; i++) {
					if (budget[i] - mid < 0)
						sum += budget[i];
					else
						sum += mid;
				}

				if (sum > M) {
					max = mid;
				} else {
					min = mid + 1;
				}
			}
			System.out.println(min - 1);
		} else {
			System.out.println(max);
		}
	}
}
