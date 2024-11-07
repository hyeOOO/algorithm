import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, result;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 각 위치에서 왼쪽과 오른쪽의 최대 높이를 구하고,
        // 그 중 더 낮은 높이에서 현재 위치의 높이를 뺀 값이 고이는 빗물의 양
        for (int i = 1; i < W - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            
            // 왼쪽에서 가장 높은 블록 찾기
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            
            // 오른쪽에서 가장 높은 블록 찾기
            for (int j = i + 1; j < W; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            
            // 현재 위치에 고이는 빗물 계산
            int minHeight = Math.min(leftMax, rightMax);
            if (minHeight > arr[i]) {
                result += minHeight - arr[i];
            }
        }
        
        System.out.println(result);
    }
}