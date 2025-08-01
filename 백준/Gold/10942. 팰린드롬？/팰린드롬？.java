import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        boolean[][] dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 길이가 1인 팰린드롬 (자기 자신)
        for(int i=1; i<=N; i++){
            dp[i][i] = true;
        }

        // 길이가 2인 팰린드롬
        for(int i=1; i<=N-1; i++){
            if(arr[i] == arr[i+1]){
                dp[i][i+1] = true;
            }
        }

        // 길이가 3 이상인 팰린드롬
        // 길이순으로 계산해야 dp[i+1][j-1]이 미리 계산되어 있음
        for(int len=3; len<=N; len++){
            for(int i=1; i<=N-len+1; i++){
                int j = i+len-1;
                if(arr[i] == arr[j] && dp[i+1][j-1]){
                    dp[i][j] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[s][e] ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}