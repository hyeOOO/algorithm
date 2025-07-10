import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long M = Long.parseLong(st.nextToken());
			long[] candy = new long[N];
			
			st = new StringTokenizer(br.readLine());
			long max = Long.MIN_VALUE;
			for(int i=0; i<N; i++) {
				candy[i] = Long.parseLong(st.nextToken());
				max = Math.max(max, candy[i]);
			}
			
			long answer = getCandyBag(candy, M, max);
			
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
	
	public static long getCandyBag(long[] candy, long m, long max) {
		long left = 1L;
		long right = max;
		long result = 0;
		
		while(left<=right) {
			long mid = (left+right)/2;
			
			if(mid==0) break;
			long count = 0L;
			for(int i=0; i<candy.length; i++) {
				count+=(candy[i]/mid);
			}
			
			if(count>=m) {
				result = mid;
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		return result;
	}
}