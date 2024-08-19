package baekjoon;

import java.util.Scanner;

public class Baekjoon2609 {
	private int gcd(int n1, int n2) {
		while(true) {
			if(n2 == 0) return n1;
			int tmp = n2;
			n2 = n1 % n2 ;
			n1 = tmp;
		}
	}
	public static void main(String[] args) {
		Baekjoon2609 M = new Baekjoon2609();
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int num1 = a >= b ? a : b;
		int num2 = num1==a ? b : a;
		
		System.out.println(M.gcd(num1, num2));
		System.out.println(num1 * num2 / M.gcd(num1, num2));
		
	}
}
