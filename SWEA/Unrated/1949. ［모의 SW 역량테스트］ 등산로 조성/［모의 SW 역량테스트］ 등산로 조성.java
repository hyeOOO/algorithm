import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, K, result;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			int startValue = Integer.MIN_VALUE;
			List<int[]> startList = new ArrayList<>();

			result = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					startValue = Math.max(startValue, map[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == startValue) {
						startList.add(new int[] { i, j });
					}
				}
			}

			for (int[] start : startList) {
				visited = new boolean[N][N];
				visited[start[0]][start[1]] = true;
				dfs(start[0], start[1], 1, false);
			}

			sb.append('#').append(t).append(' ').append(result).append('\n');
		}

		System.out.println(sb.toString());
	}

	/**
	 * @param y 
	 * @param x
	 * @param k : 깎는 횟수
	 * @param step : 진행 횟수
	 * @param isCut : 깎았는지 여부
	 */
	public static void dfs(int y, int x, int step, boolean isCut) {
		result = Math.max(result, step);

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx]) {
				if (map[y][x] > map[ny][nx]) {
					visited[ny][nx] = true;
					dfs(ny, nx, step + 1, isCut);
					visited[ny][nx] = false;
				} else if (!isCut) {
					int originalHeight = map[ny][nx];
					for (int cut = 1; cut <= K; cut++) {
						if (map[y][x] > map[ny][nx] - cut) {
							visited[ny][nx] = true;
							map[ny][nx] -= cut;
							dfs(ny, nx, step + 1, true);
							map[ny][nx] = originalHeight;
							visited[ny][nx] = false;
						}
					}
				}
			}
		}
	}
}
