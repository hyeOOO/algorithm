package baekjoon;

import java.util.Scanner;

public class Baekjoon15829 {
	private static int PRIME = 1234567891; //해시함수나 모듈러연산 등에서 충돌 혹은 오버플로우 방지에 적합

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		sc.nextLine();

		char[] chrArr = sc.nextLine().toCharArray();
		long sum = 0;
		long pow = 1;
		for (int i = 0; i < num; i++) {
			sum += (chrArr[i] - 96) * pow % PRIME;
			pow = pow * 31 % PRIME;
		}
		long hash = sum % PRIME;
		System.out.println(hash);
	}
}
