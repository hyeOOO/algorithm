// 시도도 못함.. 어떻게 접근해야하는지 감이 안왔음.
// https://girawhale.tistory.com/3
// stair 배열 초기화 시 1부터 시작해야함

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] stair = new int[301];
		int[] score = new int[301];

		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		score[1] = stair[1];
		score[2] = stair[1] + stair[2];
		score[3] = Math.max(stair[1], stair[2]) + stair[3];

		for (int j = 4; j <= n; j++) {
			score[j] = Math.max(score[j - 3] + stair[j - 1], score[j - 2]) + stair[j];
		}

		System.out.println(score[n]);
	}
}
