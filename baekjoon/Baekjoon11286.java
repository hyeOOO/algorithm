package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(v1, v2) -> Math.abs(v1) == Math.abs(v2) ? v1 - v2 : Math.abs(v1) - Math.abs(v2));

		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (!pq.isEmpty()) {
					sb.append(pq.poll()).append('\n');
				} else {
					sb.append(0).append('\n');
				}
			} else {
				pq.offer(num);
			}
		}

		System.out.println(sb);
	}
}
