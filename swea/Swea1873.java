package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1873 {
	static int H, W;
	static char[][] map;
	static char[] cmd;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}

			int startY = -1, startX = -1;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == 'v' || map[i][j] == '^') {
						startY = i;
						startX = j;
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			cmd = new char[N];

			String cmdStr = br.readLine();

			for (int i = 0; i < N; i++) {
				cmd[i] = cmdStr.charAt(i);
			}

			startGame(startY, startX);

			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	public static void startGame(int startY, int startX) {
		int curY = startY;
		int curX = startX;
		for (int i = 0; i < cmd.length; i++) {
			if (cmd[i] == 'U') { // 이동로직
				int nextY = curY - 1;
				if (nextY < 0 || map[nextY][curX] != '.') {
					map[curY][curX] = '^';
					continue;
				}
				map[curY][curX] = '.';
				map[nextY][curX] = '^';
				curY = nextY;
			} else if (cmd[i] == 'D') {
				int nextY = curY + 1;
				if (nextY >= H || map[nextY][curX] != '.') {
					map[curY][curX] = 'v';
					continue;
				}
				map[curY][curX] = '.';
				map[nextY][curX] = 'v';
				curY = nextY;
			} else if (cmd[i] == 'L') {
				int nextX = curX - 1;
				if (nextX < 0 || map[curY][nextX] != '.') {
					map[curY][curX] = '<';
					continue;
				}
				map[curY][curX] = '.';
				map[curY][nextX] = '<';
				curX = nextX;
			} else if (cmd[i] == 'R') {
				int nextX = curX + 1;
				if (nextX >= W || map[curY][nextX] != '.') {
					map[curY][curX] = '>';
					continue;
				}
				map[curY][curX] = '.';
				map[curY][nextX] = '>';
				curX = nextX;
			} else if (cmd[i] == 'S') { // 포탄발사 로직
				shoot(map[curY][curX], curY, curX);
			}
		}
	}

	public static void shoot(char direction, int y, int x) {
		if (direction == '^') {
			if (y == 0)
				return;
			for (int i = y - 1; i >= 0; i--) {
				if (map[i][x] == '#')
					return;
				if (map[i][x] == '*') {
					map[i][x] = '.';
					return;
				}
			}

		} else if (direction == '>') {
			if (x == W - 1)
				return;
			for (int i = x + 1; i < W; i++) {
				if (map[y][i] == '#')
					return;
				if (map[y][i] == '*') {
					map[y][i] = '.';
					return;
				}
			}
		} else if (direction == '<') {
			if (x == 0)
				return;
			for (int i = x - 1; i >= 0; i--) {
				if (map[y][i] == '#')
					return;
				if (map[y][i] == '*') {
					map[y][i] = '.';
					return;
				}
			}
		} else if (direction == 'v') {
			if (y == H - 1)
				return;
			for (int i = y + 1; i < H; i++) {
				if (map[i][x] == '#')
					return;
				if (map[i][x] == '*') {
					map[i][x] = '.';
					return;
				}
			}
		}
	}
}
