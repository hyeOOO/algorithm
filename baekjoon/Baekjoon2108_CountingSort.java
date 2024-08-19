//1차시도로 직접 Arrays.sort 로 풀었으나 시간초과로 인해 인터넷 참조 후 카운팅 정렬로 품.
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2108_CountingSort {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 입력값의 범위 : -4000~4000
		int[] arr = new int[8001];

		/*
		 * sum = 총 합계 max = 최댓값 min = 최솟값 median = 중앙값 mode = 최빈값
		 */
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		// median 과 mode 는 -4000~4000 을 제외한 수로 초기화하면 된다.
		int median = 10000;
		int mode = 10000;

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine());
			sum += value;
			// 배열의 index는 양수만 가능한 반면, 입력값의 범위는 음수가 될 수 있기때문에 +4000
			arr[value + 4000]++;

			if (max < value)
				max = value;
			if (min > value)
				min = value;
		}

		// 중복값에 대해 넘기기 해당 구간을 넘기기 위한 변수
		int count = 0;
		// 최대 최빈값
		int mode_max = 0;
		// 최빈값 중복 시 true
		boolean flag = false;

		for (int i = min + 4000; i <= max + 4000; i++) {
			if (arr[i] > 0) {
				// 중앙값 찾기 - 누적횟수가 전체 길이의 절반에 못미칠때
				if (count < (N + 1) / 2) {
					count += arr[i];
					median = i - 4000;
				}
				// 최빈값 찾기
				if (mode_max < arr[i]) {
					mode_max = arr[i];
					mode = i - 4000;
					flag = true;
				} else if (mode_max == arr[i] && flag == true) {
					mode = i - 4000;
					flag = false;
				}
			}
		}

		System.out.println((int) Math.round((double) sum / N)); // 산술평균
		System.out.println(median); // 중앙값
		System.out.println(mode); // 최빈값
		System.out.println(max - min); // 범위
	}
}
