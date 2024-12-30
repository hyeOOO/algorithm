import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[N+1][C+1001]; // C명보다 더 많은 고객을 유치할 수 있으므로 여유를 둠
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2); // 오버플로우 방지를 위해 2로 나눔
        }
        dp[0][0] = 0;
        
        int[] cost = new int[N+1];
        int[] customer = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= C+1000; j++) {
                dp[i][j] = dp[i-1][j]; // i번째 도시를 선택하지 않는 경우
                if (j >= customer[i]) { // 홍보 비용이 유효할 때
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-customer[i]] + cost[i]); // i번째 도시를 선택하는 경우
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-customer[i]] + cost[i]); // i번째 도시를 처음으로 선택하는 경우
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int j = C; j <= C+1000; j++) {
            result = Math.min(result, dp[N][j]);
        }
        
        System.out.println(result);
    }
}