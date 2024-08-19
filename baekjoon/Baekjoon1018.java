package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1018 {
	public static boolean[][] BOARD;
	public static int MIN = 64;

	public static void main(String[] args) throws IOException {
		Baekjoon1018 Main = new Baekjoon1018();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		BOARD = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == 'W') {
					BOARD[i][j] = true;
				} else {
					BOARD[i][j] = false;
				}
			}
		}

		int startX = M - 7;
		int startY = N - 7;

		for (int i = 0; i < startY; i++) {
			for (int j = 0; j < startX; j++) {
				Main.find(j, i);
			}
		}
		
		System.out.println(MIN);
	}

	public void find(int x, int y) {
		int endX = 8 + x;
		int endY = 8 + y;
		int count = 0;

		boolean isColor = BOARD[y][x];

		for (int i = y; i < endY; i++) {
			for (int j = x; j < endX; j++) {
				if(BOARD[i][j] != isColor) {
					count++;
				}
				isColor = !isColor;
			}
			isColor = !isColor;
		}
		count = Math.min(count, 64-count);
		MIN = Math.min(MIN, count);
	}
}
