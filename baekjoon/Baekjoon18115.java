package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon18115 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> dq = new ArrayDeque<Integer>();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
		
		for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                dq.addFirst(i);
            } else if (num == 2) {
                int top = dq.removeFirst();
                dq.addFirst(i);
                dq.addFirst(top);
            } else {
            	dq.addLast(i);
            }
        }

		StringBuilder sb = new StringBuilder();
        while (dq.size() != 0) {
            sb.append(dq.removeFirst() + " ");
        }

        System.out.println(sb);
	}
}
