// OI가 N번만큼 반복되므로 OI를 Pn이 존재할때 맨앞 OI를  *같은 특수문자로 처리하고 카운트를 증가하는 방식하려고 했으나 실패
// 이거 진짜 알고리즘 하나도 못세우겠음.....
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon5525 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		char[] arr = br.readLine().toCharArray();
		int[] memo = new int[M];
		int answer = 0;

		for (int i = 1; i < M - 1; i++) {
			if (arr[i] == 'O' && arr[i + 1] == 'I') {
				memo[i + 1] = memo[i - 1] + 1;
				if (memo[i + 1] >= N && arr[i - 2 * N + 1] == 'I')
					answer++;
			}
		}

		System.out.println(answer);
	}
}
