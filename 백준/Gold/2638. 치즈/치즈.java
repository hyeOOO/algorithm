import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M]; // 맵 초기화
        
        // 맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0;
        // 치즈가 모두 녹을 때까지 반복
        while (true) {
            // 외부 공기 표시 (0: 내부 공간, 1: 치즈, 2: 외부 공기)
            markOutsideAir();
            
            // 녹을 치즈 찾기
            List<int[]> meltList = findMeltingCheese();
            
            // 더 이상 녹을 치즈가 없으면 종료
            if (meltList.isEmpty()) {
                break;
            }
            
            // 치즈 녹이기
            for (int[] pos : meltList) {
                map[pos[0]][pos[1]] = 0;
            }
            
            time++;
        }
        
        System.out.println(time);
    }
    
    // 외부 공기 표시 (BFS)
    static void markOutsideAir() {
        // 외부 공기 표시 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0; // 외부 공기를 일반 공기로 초기화
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new int[]{0, 0}); // 시작점 (0,0)
        visited[0][0] = true;
        map[0][0] = 2; // 외부 공기로 표시
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = curr[0] + dy[d];
                int nx = curr[1] + dx[d];
                
                // 범위 내이고, 방문하지 않았으며, 치즈가 아닌 경우
                if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx] && map[ny][nx] != 1) {
                    visited[ny][nx] = true;
                    map[ny][nx] = 2; // 외부 공기로 표시
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
    }
    
    // 녹을 치즈 찾기
    static List<int[]> findMeltingCheese() {
        List<int[]> meltList = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) { // 치즈인 경우
                    int airContact = 0; // 외부 공기와 접촉한 면의 수
                    
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        
                        if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 2) {
                            airContact++;
                        }
                    }
                    
                    // 외부 공기와 2면 이상 접촉했으면 녹을 치즈 목록에 추가
                    if (airContact >= 2) {
                        meltList.add(new int[]{i, j});
                    }
                }
            }
        }
        
        return meltList;
    }
}