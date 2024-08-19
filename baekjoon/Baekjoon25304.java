package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon25304 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int total = 0;
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());

			total += price * amount;
		}

		String answer;
		if (X == total)
			answer = "Yes";
		else
			answer = "No";

		System.out.println(answer);
	}
}
