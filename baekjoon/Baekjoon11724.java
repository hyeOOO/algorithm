package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon11724 {
	static int N, M;
	static int[][] map;
	static boolean[] visit;
	static int count = 0;

	public static void dfs(int n) {
		visit[n] = true;
		for (int i = 1; i <= N; i++) {
			if (map[n][i] == 1 && visit[i] == false) {
				dfs(i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());

			map[p1][p2] = map[p2][p1] = 1;
		}

		for (int i = 1; i <= N; i++) {
			if (visit[i] == false) {
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
	}
}
