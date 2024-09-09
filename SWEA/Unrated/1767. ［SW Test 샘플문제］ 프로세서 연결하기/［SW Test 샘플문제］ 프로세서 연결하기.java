import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, result, connectCores;
	static int[][] map;
	static List<int[]> cores;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			result = Integer.MAX_VALUE;
			connectCores = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 테두리는 자동 연결
					if (i != 0 && j != 0 && i != N - 1 && j != N - 1 && map[i][j] == 1) {
						cores.add(new int[] { i, j });
					}
				}
			}

			search(0, 0, 0);

			System.out.printf("#%d %d\n", t, result);
		}
	}

	public static void search(int depth, int coreCnt, int lineCnt) {
		if (depth == cores.size()) {
			if (coreCnt > connectCores) {
				connectCores = coreCnt;
				result = lineCnt;
			} else if (coreCnt == connectCores) {
				result = Math.min(result, lineCnt);
			}
			return;
		}

		int[] curCore = cores.get(depth);

		for (int d = 0; d < 4; d++) {
			int len = isConnect(curCore[0], curCore[1], d);
			if (len != -1) {
				// 전선 연결
				connectWire(curCore[0], curCore[1], d, len, -1);
				search(depth + 1, coreCnt + 1, lineCnt + len);
				// 연결 해제
				connectWire(curCore[0], curCore[1], d, len, 0);
			}
		}

		//해당 코어를 연결하지 않는 경우
		search(depth + 1, coreCnt, lineCnt);
	}

	public static int isConnect(int y, int x, int d) {
		int len = 0;
		int ny = y, nx = x;

		while (true) {
			ny += dy[d];
			nx += dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				break;
			if (map[ny][nx] != 0)
				return -1;
			len++;
		}

		return len;
	}

	public static void connectWire(int y, int x, int d, int len, int val) {
		int ny = y, nx = x;
		for (int i = 0; i < len; i++) {
			ny += dy[d];
			nx += dx[d];
			map[ny][nx] = val;
		}
	}
}
