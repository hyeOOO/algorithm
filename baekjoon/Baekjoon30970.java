package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon30970 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int quality = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			arr[i][0] = quality;
			arr[i][1] = price;
		}

		Arrays.sort(arr, (o1, o2) -> {
			return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
		});

		sb.append(arr[0][0]).append(' ').append(arr[0][1]).append(' ');
		sb.append(arr[1][0]).append(' ').append(arr[1][1]).append(' ').append('\n');

		Arrays.sort(arr, (o1, o2) -> {
			return o1[1] != o2[1] ?  o1[1] - o2[1] : o2[0] - o1[0];
		});
		
		sb.append(arr[0][0]).append(' ').append(arr[0][1]).append(' ');
		sb.append(arr[1][0]).append(' ').append(arr[1][1]).append(' ');
		
		System.out.println(sb);

	}
}
