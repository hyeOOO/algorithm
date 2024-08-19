// 1차시도 : 시간초과(제한 시간 0.5초 => 한 줄 수식 or 이분탐색 
package baekjoon;

import java.util.Scanner;

public class Baekjoon2869 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		int v = sc.nextInt();

		// v - b : 달팽이가 목표지점에 도착한 날은 미끄러짐 감안
		// a - b : 하루 이동거리
		int day = (v - b) / (a - b);
		// 나눠떨어지지 않으면 하루를 더 count
		day = (v - b) % (a - b) != 0 ? day + 1 : day;

		System.out.println(day);
	}
}
