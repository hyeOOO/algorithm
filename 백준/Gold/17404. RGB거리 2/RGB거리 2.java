import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N+1][4];
        int[][] dp = new int[N+1][4];
        int answer = Integer.MAX_VALUE;

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
            cost[i][3] = Integer.parseInt(st.nextToken());
        }

//        for(int i=1; i<=N; i++){
//            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3])+cost[i][1];
//            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3])+cost[i][2];
//            dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2])+cost[i][3];
//        }

        for(int i=1; i<=3; i++){
            for(int j=1; j<=N; j++){
                Arrays.fill(dp[j], 1001);
            }

            dp[1][i] = cost[1][i]; // 첫번째 집 색깔 고정

            for(int j=2; j<=N; j++){
                dp[j][1] = Math.min(dp[j-1][2], dp[j-1][3])+cost[j][1];
                dp[j][2] = Math.min(dp[j-1][1], dp[j-1][3])+cost[j][2];
                dp[j][3] = Math.min(dp[j-1][2], dp[j-1][1])+cost[j][3];
            }

            for(int j=1; j<=3; j++){
                if(i==j) continue;
                answer = Math.min(answer, dp[N][j]);
            }
        }

        System.out.println(answer);
    }
}
