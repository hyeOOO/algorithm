package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11728 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arrA = new int[N];
		int[] arrB = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < M; j++) {
			arrB[j] = Integer.parseInt(st.nextToken());
		}

		int p1 = 0, p2 = 0;

		while (p1 < N && p2 < M) {
			if (arrA[p1] <= arrB[p2])
				sb.append(arrA[p1++]).append(' ');
			else if (arrA[p1] > arrB[p2])
				sb.append(arrB[p2++]).append(' ');
		}

		if (p1 == N) {
			for (int j = p2; j < M; j++) {
				sb.append(arrB[j]).append(' ');
			}
		}
		if(p2==M) {
			for(int i=p1; i<N; i++) {
				sb.append(arrA[i]).append(' ');
			}
		}
		
		System.out.println(sb);
	}
}
