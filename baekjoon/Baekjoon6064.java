// 난생처음보는 메모리초과
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int[][] calender = new int[N + 1][M + 1];

			sb.append(search(calender, M, N, y, x)).append('\n');
		}
		
		System.out.println(sb);
	}

	public static int search(int[][] calender, int M, int N, int y, int x) {
		int days = 1;
		int dx = 1;
		int dy = 1;

		while (true) {
			if (dx == x && dy == y || calender[dy][dx] != 0)
				break;
			calender[dy][dx] = days++;
			if (dx >= 0 && dx < M)
				dx++;
			else if (dx >= 0 && dx >= M)
				dx = 1;

			if (dy >= 0 && dy < N)
				dy++;
			else if (dy >= 0 && dy >= N)
				dy = 1;
		}

		if(dx == x && dy == y) return days;
		else return -1;
	}
}
