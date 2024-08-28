package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea7733_dfs {
    static int N;
    static int[][] cheese;
    static boolean[][] visited;
    static int maxTaste;
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];
            visited = new boolean[N][N];
            maxTaste = 0;
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                    maxTaste = Math.max(maxTaste, cheese[i][j]);
                }
            }
            
            int maxCheeseBlock = 1;
            
            for (int day = 0; day <= maxTaste; day++) {
                int curCheeseBlock = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cheese[i][j] > day && !visited[i][j]) {
                            dfs(i, j, day);
                            curCheeseBlock++;
                        }
                    }
                }
                maxCheeseBlock = Math.max(maxCheeseBlock, curCheeseBlock);
                
                // 모든 치즈가 먹혔다면 더 이상 진행할 필요 없음
                if (curCheeseBlock == 0) break;
                
                // visited 배열 초기화
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        visited[i][j] = false;
                    }
                }
            }
            
            sb.append("#").append(t).append(" ").append(maxCheeseBlock).append("\n");
        }
        System.out.print(sb);
    }

    public static void dfs(int y, int x, int day) {
        visited[y][x] = true;
        
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            
            if (ny >= 0 && nx >= 0 && ny < N && nx < N && cheese[ny][nx] > day && !visited[ny][nx]) {
                dfs(ny, nx, day);
            }
        }
    }
}