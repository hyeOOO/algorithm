import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H;
	static int[][][] tomato;
	static boolean[][][] visited;
	static int[] dz = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, 0, -1, 1 };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		tomato = new int[H][N][M];
		visited = new boolean[H][N][M];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomato[i][j][k] == 1) {
						visited[i][j][k] = true;
						q.offer(new int[] { i, j, k });
					}
				}
			}
		}

		System.out.println(bfs());

	}

	public static int bfs() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int z = cur[0];
			int y = cur[1];
			int x = cur[2];

			for (int d = 0; d < 6; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nz = z + dz[d];

				if (nx >= 0 && ny >= 0 && nz >= 0 && nz < H && ny < N && nx < M && tomato[nz][ny][nx] != -1) {
					if (!visited[nz][ny][nx]) {
						visited[nz][ny][nx] = true;
						q.offer(new int[] { nz, ny, nx });
						tomato[nz][ny][nx] = tomato[z][y][x] + 1;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (tomato[i][j][k] == 0)
						return -1;
					else {
						max = Math.max(max, tomato[i][j][k]);
					}
				}
			}
		}

		return max - 1;
	}
}
