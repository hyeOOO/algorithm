// 1차시도 : 틀림. 근데 생각해보니 500! 자체가 long을 넘어감. 수학적 규칙을 찾아서 품. 팩토리얼에 대한 0의 갯수는 2*5 쌍의 갯수이다..
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1676 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		while(N>=5) {
			count += N/5;
			N /= 5;
		}

		System.out.println(count);
	}
}
