package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea1208 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int[] arr = new int[100];
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < N; i++) {
				Arrays.sort(arr);
				arr[0] = arr[0] + 1;
				arr[arr.length - 1] = arr[arr.length - 1] - 1;
			}

			Arrays.sort(arr);

			int answer = arr[arr.length - 1] - arr[0];

			System.out.println("#" + t + " " + answer);
		}
	}
}
