package baekjoon;

import java.util.Scanner;

public class Baekjoon2775 {
	private int recall(int k, int n) {
		if (n == 0)
			return 0;
		if (k == 0)
			return n;
		return recall(k - 1, n) + recall(k, n - 1);
	}

	public static void main(String[] args) {
		Baekjoon2775 M = new Baekjoon2775();
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.nextLine();
		while (input > 0) {
			int k = Integer.parseInt(sc.nextLine());
			int n = Integer.parseInt(sc.nextLine());

			System.out.println(M.recall(k, n));
			input--;
		}

	}
}
