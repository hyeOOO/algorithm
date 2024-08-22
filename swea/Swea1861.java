package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1861 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int max = Integer.MIN_VALUE;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			max = Integer.MIN_VALUE;
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 1, map[i][j]);
				}
			}

			System.out.printf("#%d %d %d\n", t, answer, max);
		}
	}

	public static void dfs(int y, int x, int depth, int start) {
		if (depth > max) {
			max = depth;
			answer = start;
		}else if(depth==max && start < answer) {
			answer = start;
		}
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny >= 0 && nx >= 0 && ny < N && nx < N && map[y][x] + 1 == map[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny, nx, depth+1, start);
				visited[ny][nx] = false;
			}

		}
	}
}
