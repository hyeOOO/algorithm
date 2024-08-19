package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon14940 {
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };
	static int n, m;
	static int[][] arr;
	static boolean[][] visit;
	static int[][] result;

	public static void bfs(int y, int x) {
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

				if (cx >= 0 && cy >= 0 && cx < m && cy < n) {
					if (arr[cy][cx] == 1 && visit[cy][cx] == false) {
						q.offer(new int[] { cy, cx });
						visit[cy][cx] = true;
						result[cy][cx] = result[y][x] + 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		result = new int[n][m];
		visit = new boolean[n][m];

		int startX = -1;
		int startY = -1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					startX = j;
					startY = i;
				} else if (arr[i][j] == 0)
					visit[i][j] = true;
			}
		}

		bfs(startY, startX);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visit[i][j] == false) {
					result[i][j] = -1;
				}
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

	}
}
