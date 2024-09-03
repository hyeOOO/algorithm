import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int changeChance;
    static int maxMoney;
    static int[] digits;
    static int length;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            char[] charArray = st.nextToken().toCharArray();
            length = charArray.length;
            digits = new int[length];
            for (int i = 0; i < length; i++) {
                digits[i] = charArray[i] - '0';
            }
            changeChance = Integer.parseInt(st.nextToken());
            maxMoney = 0;
            
            // 최적화: visited 배열 크기 줄이기
            visited = new boolean[changeChance + 1][1000000];
            
            dfs(0, 0);
            sb.append("#").append(t).append(" ").append(maxMoney).append("\n");
        }
        System.out.print(sb);
    }

    public static void dfs(int depth, int swapCount) {
        int currentState = getState();
        if (visited[swapCount][currentState]) return;
        visited[swapCount][currentState] = true;

        if (swapCount == changeChance) {
            maxMoney = Math.max(maxMoney, currentState);
            return;
        }

        // 최적화: 불필요한 반복 줄이기
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                
                    swap(i, j);
                    dfs(depth + 1, swapCount + 1);
                    swap(i, j);
                
            }
        }

        // 최적화: 가지치기 - 모든 교환을 사용하지 않았을 때의 처리
        if (swapCount < changeChance && (changeChance - swapCount) % 2 == 0) {
            maxMoney = Math.max(maxMoney, currentState);
        }
    }

    public static void swap(int i, int j) {
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    public static int getState() {
        int state = 0;
        for (int digit : digits) {
            state = state * 10 + digit;
        }
        return state;
    }
}