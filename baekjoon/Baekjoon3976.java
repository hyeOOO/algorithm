package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon3976 {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		while(c-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int[][] pass = new int[n+1][2];
			int[][] dribble = new int[n+1][2];
			int[][] result = new int[n+1][2];
			
			int l1 = Integer.parseInt(st.nextToken()); // 수비수 -> 스트라이커 1 패스
			int l2 = Integer.parseInt(st.nextToken()); // 수비수 -> 스트라이커 2 패스
			int s1 = Integer.parseInt(st.nextToken()); // 스트라이커 1 -> 슛
			int s2 = Integer.parseInt(st.nextToken()); // 스트라이커 2 - > 슛
			
			// 1. 각 점에 대한 스트라이커1 -> 스트라이커2 패스 난이도
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n-1; i++) {
				pass[i][0] = Integer.parseInt(st.nextToken());
			}
			// 2.각 점에 대한  스트라이커1 드리블 난이도
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n-1; i++) {
				dribble[i][0] = Integer.parseInt(st.nextToken());
			}
			// 3. 스트라이커2 -> 스트라이커1 패스
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n-1; i++) {
				pass[i][1] = Integer.parseInt(st.nextToken());
			}
			//4. 스트라이커2 드리블
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n-1; i++) {
				dribble[i][1] = Integer.parseInt(st.nextToken());
			}
			
			result[0][0] = l1;
			result[0][1] = l2;
			
			for (int i = 1; i < n; i++) {
				result[i][0] = Math.min(result[i - 1][0] + dribble[i - 1][0], result[i - 1][1] + pass[i - 1][1]);
				result[i][1] = Math.min(result[i - 1][1] + dribble[i - 1][1], result[i - 1][0] + pass[i - 1][0]);
			}
			
			int answer = Math.min(result[n-1][0]+s1, result[n-1][1]+s2);
			System.out.println(answer);
		}
	}
}
