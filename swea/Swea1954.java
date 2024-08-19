package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swea1954 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			if (N != 1) {
				int[][] arr = new int[N][N];
				int start = 1;
				int x = 0;
				int y = 0;
				int d = 0;
				arr[x][y] = start++;

				while (true) {
					int nextX = x + dx[d % 4];
					int nextY = y + dy[d % 4];
					if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
						if (arr[nextY][nextX] != 0) {
							if (start > N * N)
								break;
							d++;
							continue;
						}
						x = nextX;
						y = nextY;
						arr[y][x] = start++;
					} else {
						d++;
					}
				}

				System.out.println("#" + t);
				for (int[] row : arr) {
					for (int n : row) {
						System.out.print(n + " ");
					}
					System.out.println();
				}
			}else {
				System.out.println("#" + t);
				System.out.println(1);
			}
		}
	}
}
