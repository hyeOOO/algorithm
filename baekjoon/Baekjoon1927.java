package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon1927 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Long> pq = new PriorityQueue<Long>();

		while (N-- > 0) {
			long num = Long.parseLong(br.readLine());
			if (num == 0) {
				if (!pq.isEmpty())
					sb.append(pq.poll()).append("\n");
				else
					sb.append(0).append("\n");
			} else
				pq.offer(num);
		}
		System.out.println(sb);
	}
}
