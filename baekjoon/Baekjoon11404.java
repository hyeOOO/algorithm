package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11404 {
	static int V, E;
	static long[][] cost;
	static long max = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		cost = new long[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j)
					cost[i][j] = 0;
				else
					cost[i][j] = max;
			}
		}

		while (E-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int fee = Integer.parseInt(st.nextToken());

			cost[a][b] = Math.min(cost[a][b], fee);
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (cost[i][j] > cost[i][k] + cost[k][j])
						cost[i][j] = cost[i][k] + cost[k][j];
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				long result = cost[i][j];
				if (result == max)
					result = 0;
				sb.append(result).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
