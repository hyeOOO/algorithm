package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @since 2024.08.27
 */
public class Swea1868 {
	static int N;
	static char[][] map;
	static int[][] count;
	static boolean[][] visited;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			count = new int[N][N];
			visited = new boolean[N][N];
			result = 0;

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			// 8방향 지뢰 수 계산
			countMines();

			// 주변에 지뢰가 없는 칸에 대해서 bfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j] && count[i][j] == 0) {
						bfs(i, j);
						result++;
					}
				}
			}

			// 나머지 빈칸들 추가 계산
			for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j]) {
                        result++;
                    }
                }
            }
			
			System.out.printf("#%d %d\n", t, result);
		}
	}
	
	public static void countMines() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '*') continue;
                int mines = 0;
                for (int d = 0; d < 8; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                    if (map[ny][nx] == '*') mines++;
                }
                count[i][j] = mines;
            }
        }
    }

	public static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { y, x });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			y = cur[0];
			x = cur[1];
			for (int d = 0; d < 8; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] == '*')
					continue;
				visited[ny][nx] = true;
				if (count[ny][nx] == 0) {
                    q.offer(new int[] { ny, nx });
                }
			}

		}
	}
}
