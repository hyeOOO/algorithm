package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16953 {
	public static long A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		System.out.println(search());
	}

	public static int search() {
		Queue<Long> q = new LinkedList<>();
		q.offer(A);
		int count = 1;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				long num = q.poll();
				if (num == B)
					return count;
				long n1 = num * 2;
				long n2 = num * 10 + 1;
				if (n1 <= B)
					q.offer(n1);
				if (n2 <= B)
					q.offer(n2);
			}
			count++;
		}
		return -1;
	}
}
