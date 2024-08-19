// 와 문제로 보니까 진짜 머리가 굳음
// 정사각형이 아니라서 더 당황스럽구요
// 왜 방향까지 있음?
// bfs+인접행렬로 품

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1012_bfs {
	static int M, N, K;
	static int[][] cabbage;
	static boolean[][] visit;
	static int count;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {
			x = q.peek()[0];
			y = q.peek()[1];
			q.poll();
			visit[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];

				if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
					if (cabbage[cx][cy] == 1 && visit[cx][cy] == false) {
						q.offer(new int[] { cx, cy });
						visit[cx][cy] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cabbage = new int[M][N];
			visit = new boolean[M][N];
			count = 0;

			while (K-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				cabbage[p1][p2] = 1;
			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (cabbage[i][j] == 1 && visit[i][j] == false) {
						bfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}
}
