package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1260 {
	static int N, M, V;
	static int[][] map;
	static boolean[] visit;
	static ArrayList<Integer> dfsList = new ArrayList<>();
	static ArrayList<Integer> bfsList = new ArrayList<>();

	public static void dfs(int i) {
		visit[i] = true;

		for (int j = 1; j <= N; j++) {
			if (map[i][j] == 1 && visit[j] == false) {
				dfsList.add(j);
				dfs(j);
			}
		}
	}

	public static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		visit[i] = true;
		q.offer(i);

		while (!q.isEmpty()) {
			int temp = q.poll();
			
			for (int j = 1; j <= N; j++) {
				if (map[temp][j] == 1 && visit[j] == false) {
					q.offer(j);
					visit[j] = true;
					bfsList.add(j);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = map[y][x] = 1;
		}

		dfsList.add(V);
		bfsList.add(V);
		dfs(V);
		visit = new boolean[N+1];
		bfs(V);

		for (int num : dfsList) {
			sb.append(num).append(" ");
		}
		sb.append("\n");
		for (int num : bfsList) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}
