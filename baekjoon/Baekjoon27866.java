package baekjoon;

import java.util.Scanner;

public class Baekjoon27866 {
	public static void main(String[] args) {
		System.out.println("백준 27866번");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int num = sc.nextInt();
		
		System.out.println(str.charAt(num-1));
	}
}
