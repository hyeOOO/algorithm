import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 첫 X일 동안의 방문자 수 계산
        int sum = 0;
        for (int i = 0; i < X; i++) {
            sum += arr[i];
        }
        
        int max = sum;
        int count = 1;
        
        // 슬라이딩 윈도우로 나머지 기간 계산
        for (int i = X; i < N; i++) {
            sum = sum + arr[i] - arr[i - X]; // 새 값 추가, 오래된 값 제거
            
            if (sum > max) {
                max = sum;
                count = 1;
            } else if (sum == max) {
                count++;
            }
        }
        
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}