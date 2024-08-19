// 다시 풀어보자.
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon1107 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] button = new int[M];
		List<Integer> useButton = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= 9; i++) {
			useButton.add(i);
		}

		for (int i = 0; i < M; i++) {
			button[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(button);

		for (int j = 0; j <= button.length; j++) {
			if (useButton.contains(button[j]))
				useButton.remove(button[j] - j);
		}

		int count = moveChannel(N, button);

	}

	public static int moveChannel(int n, int[] button) {
		if (n == 100)
			return 0;
		if (isDirectMove(n, button)) {
			String str = Integer.toString(n);
			return str.length();
		} else {

		}
		return 1;
	}

	public static boolean isDirectMove(int n, int[] button) {
		int tmp = n;
		while (tmp != 0) {
			int num = tmp % 10;
			for (int i = 0; i < button.length; i++) {
				if (button[i] == num)
					return false;
			}
			tmp /= 10;
		}
		return true;
	}

	public static int indirectMove(int n, int[] useButton) {
		int channel = 100;

		int count = 0;

		for(int i=0; i<useButton.length; i++) {
			for(int j=i; j<useButton.length; j++) {
				for(int k=j; k<useButton.length; k++) {
					for(int m=k; m<useButton.length; m++) {
						//int num = 
					}
				}
			}
		}
		return 1;
	}
}
