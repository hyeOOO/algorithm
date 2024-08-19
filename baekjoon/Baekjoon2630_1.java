package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2630_1 {
	static int white = 0;
	static int blue = 0;
	static int[][] arr;

	public static void partition(int offsetY, int offsetX, int size) {
		if (checkColor(offsetY, offsetX, size)) {
			if(arr[offsetY][offsetX]==0) white++;
			else blue++;
			return;
		} else {
			int newSize = size/2;
			partition(offsetY, offsetX, newSize);
			partition(offsetY, offsetX+newSize, newSize);
			partition(offsetY+newSize, offsetX, newSize);
			partition(offsetY+newSize, offsetX+newSize, newSize);			
		}
	}

	public static boolean checkColor(int offsetY, int offsetX, int size) {
		int firstColor = arr[offsetY][offsetX];

		for (int i = offsetY; i < offsetY + size; i++) {
			for (int j = offsetX; j < offsetX + size; j++) {
				if (arr[i][j] != firstColor)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		partition(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}
}
