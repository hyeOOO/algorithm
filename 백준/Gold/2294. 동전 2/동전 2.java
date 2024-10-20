import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp[i] = Math.min(dp[i-새동전의 값어치]+1, dp[i])
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] val = new int[n];
		int[] dp = new int[k+1];
		
		for(int i=0; i<n; i++) {
			val[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, 10001);
		dp[0] = 0;
		
		for(int i=0; i<n; i++) {
			if(val[i]>k) continue;//코인의 가치가 목표금액보다 크면 무시
			for(int j=val[i]; j<=k; j++) {
				dp[j] = Math.min(dp[j], dp[j-val[i]]+1);
			}
		}
		
		if(dp[k]==10001) System.out.println(-1);
		else System.out.println(dp[k]);
	}
}
