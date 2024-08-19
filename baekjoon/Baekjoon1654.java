// 나에게 주는 점수 : 30점.. ㅋㅋ 이분탐색을 어떻게 써야하는지 감은 잡았으나 손이 안따라줌;
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[K];

		long max = 0;
		long min = 0;
		long mid = 0;

		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (max < arr[i]) {
				max = arr[i];
			}
		}

		// if, 2개의 랜선을 가지고 있고 랜선을 길이가 [1,1]이면 min의 초기값이 0, max는 1이되므로 mid=0이 되어서 에러
		// 발생하므로 max++
		max = max + 1;

		while (max > min) {
			mid = (max + min) / 2;

			long count = 0;

			for (int i = 0; i < arr.length; i++) {
				count += (arr[i] / mid);
			}

			/*
			 * [upper bound 형식]
			 * 
			 * mid길이로 잘랐을 때의 개수가 만들고자 하는 랜선의 개수보다 작다면 자르고자 하는 길이를 줄이기 위해 최대 길이를 줄인다. 그 외에는
			 * 자르고자 하는 길이를 늘려야 하므로 최소 길이를 늘린다.
			 */
			if (count < N) {
				max = mid;
			}else {
				min = mid+1;
			}
		}
		System.out.println(min-1);
	}
}
