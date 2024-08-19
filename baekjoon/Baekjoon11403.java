package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon11403 {
	static int N;
	static long[][] cost;
	static final long maxNum = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		cost = new long[N][N];

		// 인접행렬 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Long.parseLong(st.nextToken());
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cost[i][j] == 1 || cost[i][k] + cost[k][j] == 2) {
						cost[i][j] = 1;
					}
				}
			}
		}
		
		for(long[] costs : cost) {
			for(long c : costs) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
	}
}
