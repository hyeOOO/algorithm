import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        long answer = Long.MAX_VALUE;
        long total = 0L;

        while(left<=right && left < N){
            if(total<S && right < N){
                total += arr[right++];
            }else if(total>=S){
                answer = Math.min(answer, right-left);
                total -= arr[left++];
            }else {
                break;
            }
        }

        if(answer==Long.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }
}
