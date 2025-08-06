import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] map;
    static int N = 102, M = 102; // 최대 100 * 2 = 200 이상으로 설정
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[N][M];
        visited = new boolean[N][M];
        
        // 1. 모든 좌표 2배 확대
        for (int[] rect : rectangle) {
            int sx = rect[0] * 2, sy = rect[1] * 2;
            int ex = rect[2] * 2, ey = rect[3] * 2;

            for (int y = sy; y <= ey; y++) {
                for (int x = sx; x <= ex; x++) {
                    if (y == sy || y == ey || x == sx || x == ex) {
                        if (map[y][x] != 1) map[y][x] = 2; // 테두리
                    } else {
                        map[y][x] = 1; // 내부
                    }
                }
            }
        }

        // 2. 시작점, 도착점 2배 확대
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        dfs(characterX, characterY, itemX, itemY, 0);

        return answer / 2; // 이동 칸 수는 2배 확장된 값이므로, 2로 나눔
    }
    
    public static void dfs(int x, int y, int gx, int gy, int step) {
        visited[y][x] = true;
        
        if (x == gx && y == gy) {
            answer = Math.min(answer, step);
            visited[y][x] = false;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if (visited[ny][nx]) continue;
            if (map[ny][nx] != 2) continue; // 테두리만 이동 가능

            dfs(nx, ny, gx, gy, step + 1);
        }

        visited[y][x] = false;
    }
}
