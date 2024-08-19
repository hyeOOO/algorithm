package baekjoon;

import java.util.Scanner;

public class Baekjoon2775_DP {
	static int[][] D;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();

		while (input > 0) {
			int k = sc.nextInt();
			int n = sc.nextInt();

			D = new int[k + 1][n + 1];

			for(int x=0; x<= n; x++) {	// 0층 값 초기화
				D[0][x]=x;						
			}

			//바텀-업
			for(int y=1; y<=k; y++) {
				int sum = 0;
				for(int x=0; x<=n; x++) {
					sum += D[y-1][x];
					D[y][x] = sum;
				}
			}

			System.out.println(D[k][n]);
			input--;
		}
	}
}
