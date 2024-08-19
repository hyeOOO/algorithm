package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1859_1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			int N = Integer.parseInt(br.readLine());
			long[] arr = new long[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[j] = Long.parseLong(st.nextToken());
			}
			
			long result = 0; //최대이익
			long total = 0;//매매해서 얻은 이익
			long cnt = 0;//매매횟수
			long sellingPrice = Integer.MIN_VALUE;
			
			for(int k=N-2; k>=0; k--) {
				long cur = arr[k+1];
				long pre = arr[k];
				
				if(cur>=pre||sellingPrice>pre) {
					sellingPrice = Math.max(sellingPrice, cur);
					total += pre;
					cnt++;
				}else {
					result += (sellingPrice*cnt)-(total);
					total = 0;
					cnt = 0;
				}
			}
			
			if(cnt!=0) {
				result += (sellingPrice*cnt)-(total);
			}
			
			System.out.println("#"+i+" "+ result);
		}
	}
}
