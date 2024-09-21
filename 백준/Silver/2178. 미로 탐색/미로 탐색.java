import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, result;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		result = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			char[] chr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = chr[j] - '0';
			}
		}

		result[0][0] = 1;
		bfs(0, 0);

		System.out.println(result[N-1][M-1]);
	}

	public static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			y = cur[0];
			x = cur[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx] && map[ny][nx] != 0) {
					if (ny == N && nx == M)
						return;
					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
					result[ny][nx] = result[y][x] + map[ny][nx];
				}
			}
		}
	}
}
