package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		sb.append('<');

		while (queue.size() > 1) {
			for (int i = 0; i < k - 1; i++) {
				int val = queue.poll();
				queue.offer(val);
			}

			sb.append(queue.poll()).append(", ");
		}

		sb.append(queue.poll()).append('>');
		System.out.println(sb);
	}
}
