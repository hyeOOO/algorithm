//1차시도 : 하 공식 못찾겠어서 못품 https://st-lab.tistory.com/72 보고 참고
// if문 4개 (x) if-else if로 써야 맞음
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2839 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		if (N == 4 || N == 7)
			System.out.println(-1);
		else if ((N % 5) == 0)
			System.out.println(N / 5);
		else if ((N % 5) == 1 || (N % 5) == 3)
			System.out.println((N / 5) + 1);
		else if ((N % 5) == 2 || (N % 5) == 4)
			System.out.println((N / 5) + 2);
	}
}
