// https://velog.io/@tomy8964/%EB%B0%B1%EC%A4%80-6064%EB%B2%88-%EC%B9%B4%EC%9E%89-%EB%8B%AC%EB%A0%A5-JAVA-%EC%9E%90%EB%B0%94
// 이거 어카냐? 백번 풀어라..
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon6064_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int lcm = M * N / gcd(M, N);

			x = x - 1;
			y = y - 1;
			int year = x;
			while (year <= lcm) {
				if (year % N == y) {
					sb.append(year + 1).append('\n');
					break;
				}
				year += M;
			}
			if (year > lcm)
				sb.append(-1).append('\n');
		}
		
		System.out.println(sb);
	}

	public static int gcd(int n1, int n2) {
		if (n2 == 0)
			return n1;
		return gcd(n2, n1 % n2);
	}
}
