package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1920 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		String nStr = br.readLine();
		StringTokenizer st = new StringTokenizer(nStr, " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int M = Integer.parseInt(br.readLine());
		String mStr = br.readLine();
		StringTokenizer st2 = new StringTokenizer(mStr, " ");
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st2.nextToken());
			sb.append(binarySearch(arr, num)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int binarySearch(int[] arr, int num) {
		int start = 0;
		int end = arr.length;

		while (end > start) {
			int mid = (start + end) / 2;
			if (arr[mid] == num) {
				return 1;
			}else if(arr[mid] > num) {
				end = mid;
			}else {
				start = mid + 1;
			}
		}
		return 0;
	}
}
