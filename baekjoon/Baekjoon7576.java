// 시작지점이 여러개 일 때 queue에 그냥 넣으면 된다는걸 알게됨.
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7576 {
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int M, N;
	static int[][] tomato;
	static boolean[][] visited;
	static Queue<int[]> q = new LinkedList<>();

	public static int bfs() {
		while (!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();

			for (int i = 0; i < 4; i++) {
				int cy = y + dy[i];
				int cx = x + dx[i];

				if (cy >= 0 && cx >= 0 && cy < N && cx < M) {
					if (tomato[cy][cx] == 0) {
						q.offer(new int[] { cy, cx });
						tomato[cy][cx] = tomato[y][x] + 1;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;

		if (checkZero())
			return -1;
		else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (max < tomato[i][j]) {
						max = tomato[i][j];
					}
				}
			}
			return max - 1;
		}
	}

	public static boolean checkZero() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 0)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomato = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1) {
					q.offer(new int[] { i, j });
				}
			}
		}
		System.out.println(bfs());
	}
}
