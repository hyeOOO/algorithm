package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon16236 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int shark = 2;
	static int level = 0;
	static int step = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int startY = 0, startX = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					startY = i;
					startX = j;
					map[i][j] = 0;
				}
			}
		}

		bfs(startY, startX);

		System.out.println(step);
	}

	public static void bfs(int y, int x) {
		while (true) {
			visited = new boolean[N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
					(o1, o2) -> o1[2] != o2[2] ? Integer.compare(o1[2], o2[2])
							: (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1])));

			pq.offer(new int[] { y, x, 0 });
			visited[y][x] = true;

			boolean eatChk = false;
			int[] target = null;

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				int curY = cur[0];
				int curX = cur[1];
				int curStep = cur[2];

				if (map[curY][curX] != 0 && map[curY][curX] < shark) {
					target = new int[] { curY, curX, curStep };
					eatChk = true;
					break;
				}

				for (int d = 0; d < 4; d++) {
					int ny = curY + dy[d];
					int nx = curX + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] > shark || visited[ny][nx])
						continue;
					pq.offer(new int[] { ny, nx, curStep + 1 });
					visited[ny][nx] = true;
				}
			}

			if (!eatChk)
				break;
			y = target[0];
			x = target[1];
			step += target[2];
			map[y][x] = 0;
			level++;

			if (level == shark) {
				level = 0;
				shark++;
			}
		}
	}
}
