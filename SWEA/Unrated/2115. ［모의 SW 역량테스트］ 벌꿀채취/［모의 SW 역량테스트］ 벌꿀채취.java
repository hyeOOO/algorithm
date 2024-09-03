import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] honey;
    static int maxHoney = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            honey = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxHoney = 0;

            // 첫 번째 일꾼의 꿀 선택
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int profit1 = getMaxHoney(honey[i], j, j + M - 1);

                    // 두 번째 일꾼의 꿀 선택 (첫 번째 일꾼과 같은 행)
                    for (int k = j + M; k <= N - M; k++) {
                        int profit2 = getMaxHoney(honey[i], k, k + M - 1);
                        maxHoney = Math.max(maxHoney, profit1 + profit2);
                    }

                    // 두 번째 일꾼의 꿀 선택 (다른 행)
                    for (int i2 = i + 1; i2 < N; i2++) {
                        for (int j2 = 0; j2 <= N - M; j2++) {
                            int profit2 = getMaxHoney(honey[i2], j2, j2 + M - 1);
                            maxHoney = Math.max(maxHoney, profit1 + profit2);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + maxHoney);
        }
    }

    public static int getMaxHoney(int[] row, int start, int end) {
        return backtracking(row, start, end, start, 0, 0);
    }

    public static int backtracking(int[] row, int start, int end, int depth, int curSum, int curProfit) {
        if (curSum > C) return 0;
        if (depth > end) {
            return curProfit;
        }

        int notChoice = backtracking(row, start, end, depth + 1, curSum, curProfit);
        int choice = backtracking(row, start, end, depth + 1, curSum + row[depth], curProfit + row[depth] * row[depth]);

        return Math.max(notChoice, choice);
    }
}
