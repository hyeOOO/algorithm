package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10815 {
	static int N, M;
	static int[] card;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		card = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(card);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		for (int j = 0; j < M; j++) {
			int num = Integer.parseInt(st.nextToken());
			if (binarySearch(num))
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}
		
		System.out.println(sb);
	}

	public static boolean binarySearch(int n) {
		int min = 0;
		int max = N;

		while (min < max) {
			int mid = (min + max) / 2;

			if (card[mid] > n) {
				max = mid;
			} else if (card[mid] < n) {
				min = mid+1;
			} else {
				return true;
			}
		}
		return false;
	}
}
