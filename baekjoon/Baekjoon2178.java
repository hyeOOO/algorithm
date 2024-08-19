package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2178 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int N, M;
	static int[][] maze;
	static boolean[][] visit;
	static int[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		visit = new boolean[N][M];
		result = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				maze[i][j] = Integer.parseInt(str[j]);
			}
		}

		result[0][0] = 1;
		bfs(0, 0);

		System.out.println(result[N - 1][M - 1]);
	}

	public static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		visit[y][x] = true;

		while (!q.isEmpty()) {
			y = q.peek()[0];
			x = q.peek()[1];
			q.poll();

			for (int k = 0; k < 4; k++) {
				int cy = y + dy[k];
				int cx = x + dx[k];

				if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
					if (maze[cy][cx] == 1 && visit[cy][cx] == false) {
						q.offer(new int[] { cy, cx });
						visit[cy][cx] = true;
						result[cy][cx] = result[y][x] + maze[cy][cx];
					}
				}
			}
		}
	}
}
