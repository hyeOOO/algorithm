package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon16199 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int birthYear = Integer.parseInt(st.nextToken());
		int birthMonth = Integer.parseInt(st.nextToken());
		int birthDay = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int nowYear = Integer.parseInt(st.nextToken());
		int nowMonth = Integer.parseInt(st.nextToken());
		int nowDay = Integer.parseInt(st.nextToken());

		int age = nowYear - birthYear;

		if (nowYear == birthYear) {
			System.out.println(age);
		} else {
			if (birthMonth > nowMonth) {
				System.out.println(age - 1);
			} else if (birthMonth == nowMonth) {
				if (birthDay <= nowDay) {
					System.out.println(age);
				} else {
					System.out.println(age - 1);
				}
			} else {
				System.out.println(age);
			}
		}
		System.out.println(age+1);
		System.out.println(age);

	}
}
