package baekjoon;

import java.util.Scanner;

public class Baekjoon15829_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		sc.nextLine();

		char[] chrArr = sc.nextLine().toCharArray();
		long result = 0;

		for (int i = 0; i < chrArr.length; i++) {
			int n = (chrArr[i] - 'a') + 1;
			result += (long) (n * Math.pow(31, i));
		}

		System.out.println(result);
	}
}
