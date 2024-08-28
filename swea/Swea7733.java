package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @since 2024.08.27
 */
public class Swea7733 {
	static int N;
	static int[][] cheese;
	static boolean[][] isEat;
	static int endDay; // 치즈 먹기를 끝낸 날
	static int maxCheeseBlock;
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			
			endDay = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
					endDay = Math.max(endDay, cheese[i][j]);
				}
			}

			// 최소한 하나의 치즈 덩어리가 존재함
			maxCheeseBlock = 1;

			for (int i = 0; i <= endDay; i++) {
				isEat = new boolean[N][N];
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						// startDay값과 같은 치즈 블럭 요정이 먹음
						if (cheese[j][k] <= i) {
							isEat[j][k] = true;
						}
					}
				}

				int curCheeseBlock = 0;
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						if (!isEat[j][k]) {
							bfs(j, k);
							curCheeseBlock++;
						}
					}
				}

				maxCheeseBlock = Math.max(maxCheeseBlock, curCheeseBlock);
			}

			System.out.printf("#%d %d\n", t, maxCheeseBlock);
		}

	}

	public static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { y, x });
		isEat[y][x] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			y = cur[0];
			x = cur[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || isEat[ny][nx])
					continue;

				q.offer(new int[] { ny, nx });
				isEat[ny][nx] = true;
			}
		}
	}
}
