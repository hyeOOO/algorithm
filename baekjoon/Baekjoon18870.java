// 위 문제를 보니 좌표 압축이라고는 되어있긴 하지만,
// 정확히는 좌표 압축 알고리즘을 활용한 ranking list를 만드는 문제
// 특히 데이터의 범위가 매우 크거나, 단순화 해야 할 일들이 있을 때 많이 쓰임
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baekjoon18870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] sortArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			arr[i] = sortArr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sortArr);

		int rank = 0;
		for (int v : sortArr) {
			if (!map.containsKey(v)) {
				map.put(v, rank);
				rank++;
			}
		}

		for (int key : arr) {
			int ranking = map.get(key);
			sb.append(ranking).append(" ");
		}

		System.out.println(sb);

	}
}
