import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] music = new int[2001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            music[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[2001][2001];

        for(int i=0; i<=N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][1] = dp[1][0] = 0;

        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                if(i==j) continue;
                int next = Math.max(i, j)+1;

                if(next>N) continue;

                if(i==0||j==0) music[0] = music[next]; //0번째 음을 불렀을 경우에는 값이 0이므로 힘든 정도는 0이다.
                dp[next][j] =  Math.min(dp[next][j], dp[i][j]+Math.abs(music[i]-music[next]));
                dp[i][next] = Math.min(dp[i][next], dp[i][j]+Math.abs(music[j]-music[next]));
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            answer = Math.min(answer, dp[i][N]);
            answer = Math.min(answer, dp[N][i]);
        }

        System.out.println(answer);
    }
}
