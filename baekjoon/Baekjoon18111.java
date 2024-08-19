// 1차 시도 : 각 땅높이의 빈도를 구해서 제일 높은 빈도에 맞춰서 업다운을 할랬는데 매우 막막..
// https://wonit.tistory.com/540을 보고 max값과 min 사이의 값 안에서의 완탐을 통해 구하는 방식 채택
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon18111 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				max = Math.max(max, num);
				min = Math.min(min, num);
			}
		}

		int answerTime = Integer.MAX_VALUE;
		int answerHeight = -1;

		for (int i = min; i <= max; i++) {
			int time = 0;
			int inventory = B;

			for (int j = 0; j < arr.length; j++) {
				for (int k = 0; k < arr[j].length; k++) {
					int diff = arr[j][k] - i;

					// diff이 남아있으면 제거
					if (diff > 0) {
						time += Math.abs(diff) * 2;
						inventory += Math.abs(diff);
					}
					// diff가 부족하면 쌓기
					else if (diff < 0) {
						time += Math.abs(diff);
						inventory -= Math.abs(diff);
					}
				}
			}
			
			if(inventory>=0) {
				if(time <= answerTime) {
					answerTime = time;
					answerHeight = i;
				}
			}
		}

		System.out.println(answerTime+" "+answerHeight);
	}
}
