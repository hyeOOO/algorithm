package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1074 {
	static int count = 0;

	public static void find(int x, int y, int size) {
		if (size == 1)
			return;
		if (y < size / 2 && x < size / 2) { // 2사분면
			find(x, y, size / 2);
		} else if (y < size / 2 && x >= size / 2) { // 1사분면
			count += (size * size / 4) * 1;
			find(x - size / 2, y, size / 2);
		} else if (y >= size / 2 && x < size / 2) { // 3사분면
			count += (size * size / 4) * 2;
			find(x, y - size / 2, size / 2);
		} else { // 4사분면
			count += (size * size / 4) * 3;
			find(x - size / 2, y - size / 2, size / 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);

		find(c, r, size);

		System.out.println(count);

	}
}
