import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 첫 번째 행 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        
        for(int j = 0; j < 3; j++) {
            maxDp[j] = minDp[j] = Integer.parseInt(st.nextToken());
        }
        
        // 두 번째 행부터 처리
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] input = new int[3];
            for(int j = 0; j < 3; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }
            
            int[] newMaxDp = new int[3];
            int[] newMinDp = new int[3];
            
            // 각 열에 대해 최대값과 최소값 계산
            newMaxDp[0] = input[0] + Math.max(maxDp[0], maxDp[1]);
            newMaxDp[1] = input[1] + Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
            newMaxDp[2] = input[2] + Math.max(maxDp[1], maxDp[2]);
            
            newMinDp[0] = input[0] + Math.min(minDp[0], minDp[1]);
            newMinDp[1] = input[1] + Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
            newMinDp[2] = input[2] + Math.min(minDp[1], minDp[2]);
            
            maxDp = newMaxDp;
            minDp = newMinDp;
        }
        
        int resultMax = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int resultMin = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
        
        System.out.println(resultMax + " " + resultMin);
    }
}