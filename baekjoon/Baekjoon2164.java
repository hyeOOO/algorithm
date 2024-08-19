package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Baekjoon2164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayDeque<Integer> dq = new ArrayDeque<>();

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			dq.add(i);
		}
		
		while(dq.size()>1) {
			dq.pollFirst();
			dq.addLast(dq.pollFirst());
		}
		
		System.out.println(dq.poll());
	}

}
