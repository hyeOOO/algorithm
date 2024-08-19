// 모든걸 int로 했다가 시간초과.
// for문의 i까지 long 형으로 변환해봄
// 1일때 0뜨길래 예외처리 직접해줌
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1789 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long S = Long.parseLong(br.readLine());

		long max = S;
		long min = 0;

		while (max > min) {
			long mid = (max + min) / 2;

			long sum = 0;
			if(mid==0 && S==1) {
				min = 2;
				break;
			}
			for (long i = 1; i <= mid; i++) {
				sum += i;
			}

			if (sum > S) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}

		System.out.println(min - 1);
	}
}
