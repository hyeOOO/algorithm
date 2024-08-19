package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1350 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		long cluster = Long.parseLong(br.readLine());
		long count = 0;
		for (int i = 0; i < N; i++) {
			if(arr[i]==0) continue;
			if (cluster >= arr[i]) {
				count++;
				continue;
			}
			long answer = arr[i] % cluster == 0 ? arr[i] / cluster : arr[i] / cluster + 1;
			count+=answer;
		}
		
		System.out.println(cluster*count);
	}
}
