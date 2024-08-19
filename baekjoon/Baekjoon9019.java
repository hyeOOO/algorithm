package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon9019 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			bfs(a, b);
		}
	}

	public static void bfs(int a, int b) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[10000];
		String[] command = new String[10000];

		q.offer(a);
		visit[a] = true;
		Arrays.fill(command, "");

		while (!q.isEmpty() && visit[b] == false) {
			int current = q.poll();

			int D = (current * 2) % 10000;
			int S = current == 0 ? 9999 : current - 1;
			int L = (current % 1000) * 10 + current / 1000;
			int R = (current / 10) + (current % 10) * 1000;

			if (visit[D] == false) {
				q.offer(D);
				visit[D] = true;
				command[D] = command[current].concat("D");
			}
			if (visit[S] == false) {
				q.offer(S);
				visit[S] = true;
				command[S] = command[current].concat("S");
			}
			if (visit[L] == false) {
				q.offer(L);
				visit[L] = true;
				command[L] = command[current].concat("L");
			}
			if (visit[R] == false) {
				q.offer(R);
				visit[R] = true;
				command[R] = command[current].concat("R");
			}
		}
		System.out.println(command[b]);
	}
}
