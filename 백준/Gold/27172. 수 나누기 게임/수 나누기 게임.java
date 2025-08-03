import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] player = new int[N];
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            player[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, player[i]);
        }

        int[] idx = new int[N+1];
        int[] nums = new int[max+1];

        for(int i=0; i<N; i++){
            nums[player[i]] = i+1;
        }

        for(int p : player){
            for(int i=p*2; i<=max; i+=p){
                if(nums[i]!=0){
                    idx[nums[i]]--;
                    idx[nums[p]]++;
                }
            }
        }

        for(int i=1; i<=N; i++){
            sb.append(idx[i]).append(" ");
        }

        System.out.println(sb);
    }
}
