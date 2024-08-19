package baekjoon;

import java.util.Scanner;

public class Baekjoon2231 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		int result = 0;

		for (int i = 1; i < num; i++) {
			int sum = 0;
			int chkNum = i;
			while (chkNum != 0) {
				sum += chkNum % 10;
				chkNum /= 10;
			}

			if (num == i + sum) {
				result = i;
				break;
			}
		}

		System.out.println(result);
	}
}
