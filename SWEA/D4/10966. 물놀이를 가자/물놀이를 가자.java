import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Solution
{
    static int N, M;
	static char[][] map;
	static int[][] step;
	static Queue<int[]> q;
	static boolean[][] visited;
	static int result;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			step = new int[N][M];
			q = new LinkedList<>();
			result = 0;

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 물 좌표를 기준으로 step에 거리를 저장
					if (map[i][j] == 'W') {
						q.offer(new int[] { i, j });
						step[i][j] = 0;
					} else {
						// 물을 제외하고 땅인 곳들 전부 최댓값으로 초기화
						step[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			bfs();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'L') {
						result += step[i][j];
					}
				}
			}

			System.out.printf("#%d %d\n", t, result);
		}
	}
    
    
	public static void bfs() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
					// 최단거리가 저장될 수 있게끔 함
					if (step[y][x] + 1 < step[ny][nx]) {
						step[ny][nx] = step[y][x] + 1;
						q.offer(new int[] {ny, nx});
					}
				}
			}
		}
	}
}