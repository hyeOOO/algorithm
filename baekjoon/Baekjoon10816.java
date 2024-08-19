// 1차 시도 : 시간초과 -> 이진탐색으로 풀어야 시간 초과 안남
// 2차 시도 : 인터넷 참조해서 중복값에 대한 upperBound와 lowerBound를 빼서 중복의 갯수를 찾아서 구함.
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10816 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nArr = new int[N];
		String nStr = br.readLine();
		StringTokenizer st = new StringTokenizer(nStr, " ");

		for (int i = 0; i < N; i++) {
			nArr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nArr);

		int M = Integer.parseInt(br.readLine());
		String mStr = br.readLine();
		StringTokenizer st2 = new StringTokenizer(mStr, " ");

		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st2.nextToken());
			sb.append(upperBound(nArr, num) - lowerBound(nArr, num)).append(' ');
		}

		System.out.println(sb);
	}

	private static int lowerBound(int[] arr, int n) {
		int start = 0;
		int end = arr.length;

		while (end > start) {
			int mid = (start + end) / 2;
			int val = arr[mid];

			if (n <= val) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	private static int upperBound(int[] arr, int n) {
		int start = 0;
		int end = arr.length;

		while (end > start) {
			int mid = (start + end) / 2;
			int val = arr[mid];
			if (n < val) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}
