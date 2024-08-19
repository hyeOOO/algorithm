// 클래스 형으로 풀기
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7576_1 {
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int N, M;
	static int[][] box;
	static Queue<Tomato> q = new LinkedList<>();

	static class Tomato {
		int x;
		int y;
		int day;

		public Tomato(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					q.offer(new Tomato(j, i, 0));
				}
			}
		}
	}

	public static void bfs() {
		int day = 0;
		while (!q.isEmpty()) {
			Tomato t = q.poll();
			day = t.day;

			for (int k = 0; k < 4; k++) {
				int nx = t.x + dx[k];
				int ny = t.y + dy[k];

				if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
					if (box[ny][nx] == 0) {
						box[ny][nx] = 1;
						q.offer(new Tomato(nx, ny, day + 1));
					}
				}
			}
		}

		if (checkTomato())
			System.out.println(day);
		else
			System.out.println(-1);
	}

	public static boolean checkTomato() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0)
					return false;
			}
		}
		return true;
	}
}
