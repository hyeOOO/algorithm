package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17144 {
	public static int[][] map;
	public static int R, C, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		int[] airCleaner = new int[2];

		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airCleaner[idx++] = i;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			spreadDust();
			operateAirCleaner(airCleaner);
		}

		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != -1)
					result += map[i][j];
			}
		}

		System.out.println(result);
	}

	public static void spreadDust() {
		int[][] addDustMap = new int[R][C];
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					int dCnt = 0;
					int dust = map[i][j] / 5;
					for (int d = 0; d < 4; d++) {
						int nx = j + dx[d];
						int ny = i + dy[d];
						if (nx >= 0 && ny >= 0 && nx < C && ny < R && map[ny][nx] != -1) {
							addDustMap[ny][nx] += dust;
							dCnt++;
						}
					}
					map[i][j] -= dust * dCnt;
				}

			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += addDustMap[i][j];
			}
		}
	}

	public static void operateAirCleaner(int[] airCleaner) {
		int headY = airCleaner[0];
		int bottomY = airCleaner[1];

		for (int i = headY - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		for (int j = 0; j < C-1; j++) {
			map[0][j ] = map[0][j+1];
		}
		for (int i = 0; i < headY; i++) {
			map[i ][C - 1] = map[i+1][C - 1];
		}
		for (int j = C - 1; j > 1; j--) {
			map[headY][j] = map[headY][j - 1];
		}
		map[headY][1] = 0;

		for (int i = bottomY + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int j = 1; j < C; j++) {
			map[R - 1][j - 1] = map[R - 1][j];
		}
		for (int i = R - 1; i > bottomY; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		for (int j = C - 1; j > 1; j--) {
			map[bottomY][j] = map[bottomY][j - 1];
		}
		map[bottomY][1] = 0;
	}
}
