package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2630 {
	private static class Count {
		public final int white;
		public final int blue;

		public Count(int white, int blue) {
			super();
			this.white = white;
			this.blue = blue;
		}

		public Count add(Count other) {
			return new Count(white + other.white, blue + other.blue);
		}
	}

	private static Count count(int offsetX, int offsetY, int size, int[][] arr) {
		int h = size / 2;
		for (int x = offsetX; x < offsetX + size; x++) {
			for (int y = offsetY; y < offsetY + size; y++) {
				if (arr[y][x] != arr[offsetY][offsetX]) {
					return count(offsetX, offsetY, h, arr).add(count(offsetX + h, offsetY, h, arr))
							.add(count(offsetX, offsetY + h, h, arr)).add(count(offsetX + h, offsetY + h, h, arr));
				}
			}
		}

		if (arr[offsetY][offsetX] == 1) {
			return new Count(0, 1);
		}
		return new Count(1, 0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Count count = count(0, 0, arr.length, arr);
		System.out.println(count.white);
		System.out.println(count.blue);
	}
}
