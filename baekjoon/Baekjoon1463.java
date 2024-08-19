//1차 시도: ...dp인걸 알아차리면 뭐하나 알고리즘 어케 써야할지 몰라서 인터넷 참고함
//2차 시도: 인터넷이랑 똑같이했는데 시간초과나서 ??했는데 min연산 시 순서가 뭐라고 그거 때문에 시간초과남;
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1463 {
	public static int[] X;

	public static int divide(int n) {
		if (X[n] == -1) {
			if (n % 6 == 0)
				X[n] = Math.min(divide(n - 1), Math.min(divide(n / 3), divide(n / 2))) + 1;
			else if (n % 3 == 0)
				X[n] = Math.min(divide(n / 3), divide(n - 1)) + 1; //min연산에서 순서바뀌면 시간초과남
			else if (n % 2 == 0)
				X[n] = Math.min(divide(n / 2), divide(n - 1)) + 1; //마찬가지
			else
				X[n] = divide(n - 1) + 1;
		}
		return X[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		X = new int[N + 1];

		Arrays.fill(X, -1);

		X[0] = 0;
		X[1] = 0;

		System.out.print(divide(N));
	}
}
