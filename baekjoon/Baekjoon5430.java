package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon5430 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Deque<Integer> dq;
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), "[],");
			dq = new LinkedList<>();

			for (int i = 0; i < n; i++) {
				dq.add(Integer.parseInt(st.nextToken()));
			}
			action(dq, p);
		}
		System.out.println(sb);
	}

	public static void action(Deque<Integer> dq, String p) {
		boolean isRight = true;

		for (char command : p.toCharArray()) {
			if (command == 'R') {
				isRight = !isRight;
				continue;
			} else {
				if (dq.isEmpty()) {
					sb.append("error").append("\n");
					return;
				}
				if (isRight) {
					dq.pollFirst();
				} else {
					dq.pollLast();
				}
			}
		}
		sb.append('[');
		if (!dq.isEmpty()) {
			if (isRight) {
				sb.append(dq.pollFirst());
				while (!dq.isEmpty())
					sb.append(',').append(dq.pollFirst());
			} else {
				sb.append(dq.pollLast());
				while (!dq.isEmpty())
					sb.append(',').append(dq.pollLast());
			}
		}
		sb.append(']').append('\n');
	}

}
