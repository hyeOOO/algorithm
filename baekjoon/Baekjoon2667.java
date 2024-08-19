package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon2667 {
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static int N;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		int complex = 0;
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == false) {
					complex++;
					list.add(bfs(i, j));
				}
			}
		}

		Collections.sort(list);
		
		sb.append(complex).append('\n');
		for (int l : list) {
			sb.append(l).append('\n');
		}
		System.out.println(sb);
	}

	public static int bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		visit[y][x] = true;
		int count = 1;

		while (!q.isEmpty()) {
			y = q.peek()[0];
			x = q.peek()[1];
			q.poll();

			for (int k = 0; k < 4; k++) {
				int cy = y + dy[k];
				int cx = x + dx[k];

				if (cx >= 0 && cy >= 0 && cx < N && cy < N) {
					if (map[cy][cx] == 1 && visit[cy][cx] == false) {
						q.offer(new int[] { cy, cx });
						visit[cy][cx] = true;
						count++;
					}
				}
			}
		}

		return count;
	}
}
