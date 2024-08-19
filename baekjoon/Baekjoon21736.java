package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon21736 {
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int N, M;
	static char[][] campus;
	static boolean[][] visit;
	static int count = 0;

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		visit[y][x] = true;

		while (!q.isEmpty()) {
			y = q.peek()[0];
			x = q.peek()[1];
			q.poll();

			for (int k = 0; k < 4; k++) {
				int cy = dy[k] + y;
				int cx = dx[k] + x;

				if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
					if (campus[cy][cx] != 'X' && visit[cy][cx] == false) {
						if (campus[cy][cx] == 'P')
							count++;
						q.offer(new int[] { cy, cx });
						visit[cy][cx] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		campus = new char[N][M];
		visit = new boolean[N][M];

		int startX = -1;
		int startY = -1;

		for (int i = 0; i < N; i++) {
			campus[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (campus[i][j] == 'I') {
					startY = i;
					startX = j;
				} else if (campus[i][j] == 'X') {
					visit[i][j] = true;
				}
			}
		}

		bfs(startX, startY);

		if(count==0) System.out.println("TT");
		else System.out.println(count);
		
	}
}
