import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, result;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
				checkTShape(i, j);
			}
		}

		System.out.println(result);

	}

	public static void dfs(int y, int x, int depth, int sum) {
		if (depth == 4) {
			result = Math.max(result, sum);
			return;
		}

		// 시간 초과 ㅠㅠ
		//		// ㅗ 모양 블럭 만들기
		//		if (depth == 3) {
		//			int d = dir[1];
		//			boolean isDirect = true;
		//			for (int i = 1; i < depth; i++) {
		//				if (d != dir[i]) {
		//					isDirect = false;
		//					break;
		//				}
		//			}
		//
		//			if (isDirect) {
		//				// 일직선 일 때
		//				if (dy[d] == -1) { // 위쪽 직선 블럭
		//					if (x + 1 >= M)
		//						select[3] = map[y + 1][x - 1];
		//					else if (x - 1 < 0)
		//						select[3] = map[y + 1][x + 1];
		//					else
		//						select[3] = Math.max(map[y + 1][x - 1], map[y + 1][x + 1]);
		//				} else if (dy[d] == 1) { // 아래쪽 직선 블럭
		//					if (x + 1 >= M)
		//						select[3] = map[y - 1][x - 1];
		//					else if (x - 1 < 0)
		//						select[3] = map[y - 1][x + 1];
		//					else
		//						select[3] = Math.max(map[y - 1][x - 1], map[y - 1][x + 1]);
		//				} else if (dx[d] == -1) {
		//					if (y + 1 >= N)
		//						select[3] = map[y - 1][x + 1];
		//					else if (y - 1 < 0)
		//						select[3] = map[y + 1][x + 1];
		//					else
		//						select[3] = Math.max(map[y - 1][x + 1], map[y + 1][x + 1]);
		//				} else if (dx[d] == 1) {
		//					if (y + 1 >= N)
		//						select[3] = map[y - 1][x - 1];
		//					else if (y - 1 < 0)
		//						select[3] = map[y + 1][x - 1];
		//					else
		//						select[3] = Math.max(map[y - 1][x - 1], map[y + 1][x - 1]);
		//				}
		//
		//				dfs(y, x, depth + 1, select, dir);
		//			}
		//		}

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					dfs(ny, nx, depth + 1, sum + map[ny][nx]);
					visited[ny][nx] = false;
				}
			}
		}
	}

	// ㅗ 모양 따로 처리 (현재 위치에서 만들 수 있는 ㅗ 모양 4가지 형태)
	public static void checkTShape(int y, int x) {
		// 범위를 벗어나는지 체크
		if (y >= 1 && x >= 1 && x < M - 1) {
			// ㅗ
			int sum = map[y][x] + map[y - 1][x] + map[y][x - 1] + map[y][x + 1];
			result = Math.max(result, sum);
		}
		if (y >= 1 && y < N - 1 && x < M - 1) {
			// ㅏ
			int sum = map[y][x] + map[y - 1][x] + map[y][x + 1] + map[y + 1][x];
			result = Math.max(result, sum);
		}
		if (y < N - 1 && x >= 1 && x < M - 1) {
			// ㅜ
			int sum = map[y][x] + map[y][x - 1] + map[y][x + 1] + map[y + 1][x];
			result = Math.max(result, sum);
		}
		if (y >= 1 && y < N - 1 && x >= 1) {
			// ㅓ
			int sum = map[y][x] + map[y - 1][x] + map[y + 1][x] + map[y][x - 1];
			result = Math.max(result, sum);
		}
	}
}
