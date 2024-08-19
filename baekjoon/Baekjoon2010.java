package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2010 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int answer = 1;
		while (N-- > 0) {
			int input = Integer.parseInt(br.readLine());
			answer -= 1;
			answer += input;
		}
		System.out.println(answer);
	}
}
