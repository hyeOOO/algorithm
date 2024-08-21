package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @Date 2024.08.21
 * @author 박혜원 [풀이 계획] 1. N과 명령어 및 map 입력 받기 2. 명령어에 따라 모든 블록을 그 방향으로 땡기기 3.
 *         명령어에 따라 해당 방향의 n의 값과 n+1값 비교해서 같다면 합치기 4. 2번 동작 수행 후 n+2에 값이 있다면
 *         n+2값을 n+1로 땡기고 n+3도 마찬가지
 *
 */
public class Swea6109 {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String cmd = st.nextToken();
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			moveBlock(cmd);
			sumBlock(cmd);

			System.out.println("#"+t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}

		}
	}

	public static void sumBlock(String cmd) {
		if (cmd.equals("up")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[j][i] == map[j + 1][i]) {
						map[j][i] *= 2;
						map[j + 1][i] = 0;
					}
				}
			}
		} else if (cmd.equals("down")) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[j][i] == map[j - 1][i]) {
						map[j][i] *= 2;
						map[j - 1][i] = 0;
					}
				}
			}
		} else if (cmd.equals("left")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[i][j] == map[i][j + 1]) {
						map[i][j] *= 2;
						map[i][j + 1] = 0;
					}
				}
			}
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[i][j] == map[i][j - 1]) {
						map[i][j] *= 2;
						map[i][j - 1] = 0;
					}
				}
			}
		}

		moveBlock(cmd);
	}

	public static void moveBlock(String cmd) {
		if (cmd.equals("up")) {
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();

				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0)
						q.offer(map[j][i]);
				}

				int row = 0;
				while (!q.isEmpty()) {
					map[row++][i] = q.poll();
				}

				while (row < N) {
					map[row++][i] = 0;
				}
			}
		} else if (cmd.equals("down")) {
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();

				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0)
						q.offer(map[j][i]);
				}

				int row = N - 1;
				while (!q.isEmpty()) {
					map[row--][i] = q.poll();
				}

				while (row >= 0) {
					map[row--][i] = 0;
				}
			}
		} else if (cmd.equals("left")) {
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();

				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						q.offer(map[i][j]);
				}

				int row = 0;
				while (!q.isEmpty()) {
					map[i][row++] = q.poll();
				}

				while (row < N) {
					map[i][row++] = 0;
				}
			}
		} else {
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new LinkedList<>();

				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0)
						q.offer(map[i][j]);
				}

				int row = N - 1;
				while (!q.isEmpty()) {
					map[i][row--] = q.poll();
				}

				while (row >= 0) {
					map[i][row--] = 0;
				}
			}
		}
	}
}
