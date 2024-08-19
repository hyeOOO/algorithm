// 플로이드 워셜 문제
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1389 {
	static int V, E;
	static long[][] cost;
	static long MAX = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		cost = new long[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j)
					cost[i][j] = 0;
				else
					cost[i][j] = MAX;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cost[a][b] = cost[b][a] = 1;
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (cost[i][j] > cost[i][k] + cost[k][j])
						cost[i][j] = cost[i][k] + cost[k][j];
				}
			}
		}

		long temp = Long.MAX_VALUE;
		int index = 0;

		for (int i = 1; i <= V; i++) {
			long sum = 0;
			for (int j = 1; j <= V; j++) {
				sum += cost[i][j];
			}
			if (temp > sum) {
				temp = sum;
				index = i;
			}
		}
		System.out.println(index);
	}
}
