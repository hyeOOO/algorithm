import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, startX, startY, goalX, goalY;
	static char[][] map;
	static char[] cmd;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();

			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 'X') {
						startY = i;
						startX = j;
					} else if (map[i][j] == 'Y') {
						goalY = i;
						goalX = j;
					}
				}
			}

			int Q = Integer.parseInt(br.readLine());

			for (int q = 0; q < Q; q++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				cmd = new char[C];
				cmd = st.nextToken().toCharArray();

				sb.append(check()).append(' ');
			}

			System.out.printf("#%d %s\n", t, sb.toString());
		}
	}

	public static int check() {
		int d = 0;
		int y = startY;
		int x = startX;

		for (char c : cmd) {
			if (c == 'R') {
				d = (d + 1) % 4;
			} else if (c == 'L') {
				d = (d + 3) % 4;
			} else {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < N && map[ny][nx] != 'T') {
					y = ny;
					x = nx;
				}
			}
		}

		if (y == goalY && x == goalX)
			return 1;
		return 0;
	}
}
