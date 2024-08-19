package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon10026 {
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int N;
	static char[][] watch;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		watch = new char[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			watch[i] = br.readLine().toCharArray();
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == false) {
					bfs(i, j, watch[i][j]);
					count++;
				}
			}
		}
		sb.append(count).append(" ");
		count = 0;
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (watch[i][j] == 'G')
					watch[i][j] = 'R';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == false) {
					bfs(i, j, watch[i][j]);
					count++;
				}
			}
		}
		sb.append(count).append(" ");
		
		System.out.println(sb);
	}

	public static void bfs(int y, int x, char color) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		visit[y][x] = true;

		while (!q.isEmpty()) {
			y = q.peek()[0];
			x = q.peek()[1];
			q.poll();

			for (int i = 0; i < 4; i++) {
				int cy = y + dy[i];
				int cx = x + dx[i];

				if (cx >= 0 && cy >= 0 && cx < N && cy < N) {
					if (watch[cy][cx] == color && visit[cy][cx] == false) {
						q.offer(new int[] { cy, cx });
						visit[cy][cx] = true;
					}
				}
			}
		}
	}
}
