import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] F;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        F = new int[N + 1];
        F[0] = 0;
        F[1] = 1;
        
        System.out.println(fibo(N));
    }
    
    public static int fibo(int n) {
        // 이미 계산된 경우 바로 반환
        if (F[n] != 0 || n == 0) return F[n];
        
        // 값 계산 후 배열에 저장
        return F[n] = fibo(n - 1) + fibo(n - 2);
    }
}
