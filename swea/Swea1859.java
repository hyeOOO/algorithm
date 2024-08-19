package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1859 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			long[] arr = new long[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}

			long save = 0L;
			long count = 0L;
			long result = 0L;
			for (int i = 0; i < arr.length-1; i++) {
				if(arr[i]<=arr[i+1]) {
					save += arr[i];
					count++;
				}else  {
					result += (arr[i]*count)-save;
					save = 0;
					count=0;
				}
			}
			
			// 남은 물건이 있을 경우 마지막 날에 팔기
			result += arr[arr.length - 1] * count - save;
			System.out.println("#"+t+" "+ result);
		}

	}
}
