package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baekjoon11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) {
				if (!q.isEmpty())
					sb.append(q.poll()).append('\n');
				else
					sb.append(0).append('\n');
			} else {
				q.add(x);
			}
		}
		
		System.out.println(sb);
	}
}
