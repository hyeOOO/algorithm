// dfs + 백트래킹 : 존나 어려움 ;
package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1244 {
	static String[] arr;
	static int max, chance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = st.nextToken().split("");
			chance = Integer.parseInt(st.nextToken());

			max = 0;

			if (arr.length < chance) {
				chance = arr.length;
			}

			dfs(0, 0);
			System.out.println("#"+t+" "+max);
		}
	}

	public static void dfs(int start, int cnt) {
		if (cnt == chance) {
			String result = "";
			for (String s : arr) {
				result += s;
			}
			max = Math.max(max, Integer.parseInt(result));
			return;
		}

		for (int i = start; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				String temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
				dfs(i, cnt+1);
				
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}

}
