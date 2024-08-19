package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Baekjoon2798 {
	private int blackzack(int[] arr, int total) {
		Arrays.sort(arr);
		List<Integer> list = new ArrayList<Integer>();

		int result = 0;
		for (int a = 0; a < arr.length; a++) {
			if (arr[a] > total)
				break;
			for (int b = a + 1; b < arr.length; b++) {
				if (arr[a] + arr[b] > total)
					break;
				for (int c = b + 1; c < arr.length; c++) {
					if (arr[a] + arr[b] + arr[c] > total)
						break;
					list.add(total - (arr[a] + arr[b] + arr[c]));
				}
			}
		}

		Collections.sort(list);

		result = total - list.get(0);
		return result;
	}

	public static void main(String[] args) {
		Baekjoon2798 M = new Baekjoon2798();
		Scanner sc = new Scanner(System.in);

		int card = sc.nextInt();
		int total = sc.nextInt();

		sc.nextLine();

		int[] arr = new int[card];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.println(M.blackzack(arr, total));
	}
}
