import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        dp[1]=0;
        dp[2]=1;
        dp[3]=1;

        // bottom-up
        for(int i=4; i<=N; i++){
            if(i%6==0){
                dp[i] = Math.min(dp[i/3]+1, Math.min(dp[i/2]+1, dp[i-1]+1));
            }else if(i%3==0){
                dp[i] = Math.min(dp[i/3]+1, dp[i-1]+1);
            }else if(i%2==0){
                dp[i] = Math.min(dp[i/2]+1, dp[i-1]+1);
            }else{
                dp[i] = dp[i-1]+1;
            }
        }

        List<Integer> answer = new ArrayList<>();
        int cur = N;
        answer.add(cur);

        while(true){
            if(cur==1) break;
            int val = dp[cur-1];
            if(cur%3==0 && val>dp[cur/3] && dp[cur/2]>=dp[cur/3]){
                cur = cur/3;
                answer.add(cur);
            }
            else if(cur%2==0 && val>dp[cur/2]){
                cur = cur/2;
                answer.add(cur);
            }else{
                cur = cur-1;
                answer.add(cur);
            }
        }

        System.out.println(dp[N]);

        for(int r : answer){
            System.out.print(r+" ");
        }
    }
}
