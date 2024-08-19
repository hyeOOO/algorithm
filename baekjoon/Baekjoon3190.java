package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baekjoon3190 {
	public static int N;
	public static int[][] map;
	static List<int[]> snake = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			String[] str = br.readLine().split(" ");
			int y = Integer.parseInt(str[0]) - 1;
			int x = Integer.parseInt(str[1]) - 1;
			map[y][x] = 2;
		}

		int L = Integer.parseInt(br.readLine());
		Map<Integer, String> cmd = new HashMap<Integer, String>();
		for (int i = 0; i < L; i++) {
			String[] str = br.readLine().split(" ");
			cmd.put(Integer.parseInt(str[0]), str[1]);
		}

		int result = moveSnake(cmd);
		System.out.println(result);

	}

	public static int moveSnake(Map<Integer, String> cmd) {
		int time = 0;
		int[] dy = { 0, 1, 0, -1 }; // 우, 하, 좌, 상
        int[] dx = { 1, 0, -1, 0 };
		int y = 0, x = 0;
		int d = 0;
		snake.add(new int[] { y, x });

		while (true) {
			time++;

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (isFinish(ny, nx))
				break;

			// 사과가 있을 때 없을 때 처리
			if (map[ny][nx] == 2) {
				map[ny][nx] = 0;
				snake.add(new int[] { ny, nx });

			} else {
				snake.add(new int[] { ny, nx });
				snake.remove(0);
			}

			// 방향을 바꿔주는 시간을 만날 때 방향 변경
			if (cmd.containsKey(time)) {
				if (cmd.get(time).equals("D")) {
					d = (d + 1) % 4;
				} else {
					d = (d + 3) % 4;
				}
			}

			x = nx;
			y = ny;
		}

		return time;
	}

	public static boolean isFinish(int ny, int nx) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return true;
		}

		for (int i = 0; i < snake.size(); i++) {
			int[] t = snake.get(i);
			if (ny == t[0] && nx == t[1])
				return true;
		}
		return false;
	}
}
