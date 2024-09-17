import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];
        
        // 1부터 N까지의 최소 연산 횟수 계산
        for (int i = 2; i <= N; i++) {
            // 먼저 1을 빼는 경우
            DP[i] = DP[i - 1] + 1;

            // 2로 나누어 떨어지면 그 경우도 고려
            if (i % 2 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 2] + 1);
            }

            // 3으로 나누어 떨어지면 그 경우도 고려
            if (i % 3 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 3] + 1);
            }
        }

        // 결과 출력
        System.out.println(DP[N]);
    }
}

