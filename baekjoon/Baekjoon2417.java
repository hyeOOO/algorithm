// lower Bound로 풀어야함.
// uppder Bound -> 시간초과

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2417 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());

		long min = 0;
		long max = n;

		while (min < max) {
			long mid = (min + max) / 2;

			if (Math.pow(mid, 2) < n) {
				min = mid + 1;
			} else {
				max = mid;
			}
		}

		System.out.println(min);
	}
}
