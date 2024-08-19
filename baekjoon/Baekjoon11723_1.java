package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11723_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = Integer.parseInt(br.readLine());
		int S = 0;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String expr = st.nextToken();
			int num = 0;
			if (!expr.equals("all") && !expr.equals("empty")) {
				num = Integer.parseInt(st.nextToken());
			}
			
			switch (expr) {
			case "add":
				S |= (1 << num);
				break;
			case "remove":
				S &= ~(1 << num);
				break;
			case "check":
				if ((S & (1 << num)) != 0)
					System.out.println(1);
				else
					System.out.println(0);
				break;
			case "toggle":
				S ^= (1 << num);
				break;
			case "all":
				S = (1 << 21) - 1;
				break;
			case "empty":
				S = 0;
				break;
			}
		}
	}
}
