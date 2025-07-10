import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int dx = Math.abs(Integer.parseInt(st.nextToken())-Integer.parseInt(st.nextToken()));
			
			int[] cows = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				cows[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(cows);
			
			int min = Integer.MAX_VALUE;
			int count = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				int hPos = Integer.parseInt(st.nextToken());
				int X = binarySearch(cows, hPos); // 현재 말 위치에서 제일 가까운 !!왼쪽!! 소의 인덱스
				
				if(X<cows.length) {
					int dist = cows[X]-hPos;
					if(min==dist) {
						count++;
					}else if(min>dist) {
						min = dist;
						count = 1;
					}
				}
				
				if(X-1>=0) {
					int dist = hPos-cows[X-1];
					if(min==dist) {
						count++;
					}else if(min>dist) {
						min = dist;
						count = 1;
					}
				}
			}
			
			System.out.printf("#%d %d %d\n",tc, dx+min, count);
		}
	}
	
	public static int binarySearch(int[] cows, int pos) {
		int ans = cows.length;
		
		int left = 0, right = cows.length-1;
		
		while(left<=right) {
			int mid = (left+right)/2;
			if(cows[mid]>=pos) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		
		return ans;
	}
}