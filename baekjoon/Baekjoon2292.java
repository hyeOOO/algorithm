package baekjoon;

import java.util.Scanner;

public class Baekjoon2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();

		int layer = 1;
		int range = 2;

		if (input == 1) {
			System.out.println(1);
		} else {
			while (range <= input) {
				range = range + (layer * 6);
				layer++;
			}
			System.out.println(layer);
		}
	}
}
