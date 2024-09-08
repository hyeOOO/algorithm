import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] tree = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(tree);

			int goal = tree[tree.length - 1];

			long need1 = 0;
			long need2 = 0;

			for (int i = 0; i < N; i++) {
				int need = goal - tree[i];
				need2 += need / 2;
				need1 += need % 2;
			}

			while (need2 > need1 + 1) { // 2일만을 기다리는게 아니라 1일을 두번처리 하는 방식을 채택하여 1과 2의 차이 최소화하기
				need2--;
				need1 += 2;
			}

			long result;
			if (need1 == need2) {
				result = need2 * 2;
			} else if (need1 > need2) { // 무조건 1일이 오기를 기다려야 함.
				result = need1 * 2 - 1;
			} else {
				result = need2 * 2;
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
	}
}
