import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int [][] input = new int[M + 1][2];
        dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int pages = Integer.parseInt(st.nextToken());

            input[i][0] = day;
            input[i][1] = pages;
        }

        for (int i = 1; i <= M; i++) {
            int day = input[i][0];
            int pages = input[i][1];

            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j];
                if ((j - day) >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - day] + pages);
                }
            }
        }
        System.out.print(dp[M][N]);
    }
}