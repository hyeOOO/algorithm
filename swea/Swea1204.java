package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1204 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			int[] arr = new int[101];
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 1000; j++) {
				int score = Integer.parseInt(st.nextToken());
				arr[score]++;
			}

			int answer = Integer.MIN_VALUE;
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (count <= arr[j]) {
					count = arr[j];
					answer = j;
				}
			}

			System.out.println("#" + i + " " + answer);
		}
	}
}
