package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7569 {
	static final int[] dx = { 0, 1, 0, -1, 0, 0 };
	static final int[] dy = { 1, 0, -1, 0, 0, 0 };
	static final int[] dz = { 0, 0, 0, 0, 1, -1 };

	static int N, M, H;
	static int[][][] tomato;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		tomato = new int[H][N][M];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < M; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomato[i][j][k] == 1)
						q.offer(new int[] { i, j, k });
				}
			}
		}

		System.out.println(bfs());
	}

	public static int bfs() {
		while (!q.isEmpty()) {
			int z = q.peek()[0];
			int y = q.peek()[1];
			int x = q.peek()[2];
			q.poll();

			for (int k = 0; k < 6; k++) {
				int cz = z + dz[k];
				int cy = y + dy[k];
				int cx = x + dx[k];

				if (cx >= 0 && cy >= 0 && cz >= 0 && cx < M && cy < N && cz < H) {
					if (tomato[cz][cy][cx] == 0) {
						q.offer(new int[] { cz, cy, cx });
						tomato[cz][cy][cx] = tomato[z][y][x] + 1;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;

		if (checkZero()) {
			return -1;
		} else {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						if (max < tomato[i][j][k])
							max = tomato[i][j][k];
					}
				}
			}
			return max - 1;
		}
	}

	public static boolean checkZero() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (tomato[i][j][k] == 0)
						return true;
				}
			}
		}
		return false;
	}
}
