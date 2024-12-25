import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=  new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] I = new int[K+1];
		int[] T = new int[K+1];
		int[][] dp = new int[K+1][N+1];
		
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			I[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				if(T[i]>j) {
					dp[i][j] = dp[i-1][j];
				}
				
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-T[i]]+I[i]);
				}
			}
		}
		
		System.out.println(dp[K][N]);
		
		
	}
}
