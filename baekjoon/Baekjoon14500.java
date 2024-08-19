//하... 백트래킹..

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14500 {
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	public static int N, M;
	public static int[][] map;
	public static boolean[][] visit;
	public static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visit[i][j] = false;
				check(i, j);
			}
		}
		System.out.println(max);
	}

	public static void dfs(int y, int x, int depth, int sum) {
		if (depth >= 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int cy = y + dy[i];
			int cx = x + dx[i];

			if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
				if (visit[cy][cx] == false) {
					visit[cy][cx] = true;
					dfs(cy, cx, depth + 1, sum + map[cy][cx]);
					visit[cy][cx] = false;
				}
			}
		}
	}

	public static void check(int y, int x) {
		if (y < N - 2 && x < M - 1)
            max = Math.max(max, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x + 1]);

        if (y < N - 2 && x > 0)
            max = Math.max(max, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x - 1]);

        if (y < N - 1 && x < M - 2)
        	max = Math.max(max, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 1]);

        if (y > 0 && x < M - 2)
        	max = Math.max(max, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y - 1][x + 1]);
	}
}
