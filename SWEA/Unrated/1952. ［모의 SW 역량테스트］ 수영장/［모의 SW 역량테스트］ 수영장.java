import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] cost, plan;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			cost = new int[4];
			plan = new int[13];

			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			result = cost[3];

			process(1, 0);

			System.out.printf("#%d %d\n", t, result);
		}
	}

	public static void process(int month, int sum) {
		if (result <= sum)
			return;
		if (month > 12) {
			result = Math.min(result, sum);
			return;
		}

		if (plan[month] == 0) {
			process(month + 1, sum);
		} else {
			//1일 이용권 사용
			process(month + 1, sum + plan[month] * cost[0]);

			//1달 이용권 사용
			process(month + 1, sum + cost[1]);

			//3달 이용권 사용
			process(month + 3, sum + cost[2]);
		}
	}
}
