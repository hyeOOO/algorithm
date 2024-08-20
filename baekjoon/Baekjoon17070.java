package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17070 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 0, 1 };
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		dfs(0, 1, 0);

		System.out.println(count);
	}

	public static void dfs(int y, int x, int direction) {
		if (y == N - 1 && x == N - 1) {
			count++;
			return;
		}

		for (int d = 0; d < 3; d++) {
			if (direction == 0 && d == 1)
				continue; // 이전 방향이 가로면 세로 불가
			else if (direction == 1 && d == 0)
				continue;// 이전 방향이 세로면 가로 불가
			else if (d == 2) {
				int ny1 = y + dy[0];
				int nx1 = x + dx[0];
				int ny2 = y + dy[1];
				int nx2 = x + dx[1];

				if (ny1 < 0 || nx1 < 0 || ny1 >= N || nx1 >= N || ny2 < 0 || nx2 < 0 || ny2 >= N || nx2 >= N
						|| map[ny1][nx1] == 1 || map[ny2][nx2] == 1)
					continue;
			}
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny >= 0 && nx >= 0 && ny < N && nx < N && map[ny][nx] != 1) {
				dfs(ny, nx, d);
			}
		}
	}
}
