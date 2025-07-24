import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] spendMoney = new long[N];
        long total = 0L;
        long maxSpend = 0L;

        for(int i=0; i<N; i++){
            spendMoney[i] = Long.parseLong(br.readLine());
            total += spendMoney[i];
            maxSpend = Math.max(maxSpend, spendMoney[i]);
        }

        long left = maxSpend;
        long right = total;
        long result = total;

        while(left<=right){
            long mid = (left+right)/2;

            if(canWithdraw(spendMoney, N, M, mid)){
                result = mid;
                right = mid - 1;  // 더 작은 값 탐색
            } else {
                left = mid + 1;   // 더 큰 값 탐색
            }
        }

        System.out.println(result);

    }

    private static boolean canWithdraw(long[] spendMoney, int N, int M, long amount) {
        int count = 1;  // 첫 번째 인출
        long curMoney = amount;

        for(int i = 0; i < N; i++){
            if(curMoney >= spendMoney[i]){
                curMoney -= spendMoney[i];
            } else {
                count++;
                curMoney = amount - spendMoney[i];

                // 한 번에 인출한 금액으로도 지출할 수 없는 경우
                if(curMoney < 0) {
                    return false;
                }
            }
        }

        return count <= M;
    }
}
