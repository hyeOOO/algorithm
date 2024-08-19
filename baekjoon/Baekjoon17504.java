// 제리와 톰2 - 기본 수학, 구현 문제
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17504 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long denom = arr[N - 1];
		long numer = 1;
		
		for (int i = N - 2; i >= 0; i--) {
			numer = (arr[i] * denom) + numer;
			long temp = denom;
			denom = numer;
			numer = temp;
		}
		
		System.out.println(denom-numer + " " + denom);
	}
}
