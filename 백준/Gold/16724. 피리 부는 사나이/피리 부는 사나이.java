import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] visited; // 0: 미방문, 1: 방문중, 2: 완료
    static boolean[][] safezone;
    static int safeZoneCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];
        safezone = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] == 0){
                    dfs(i, j);
                }
            }
        }

        System.out.println(safeZoneCount);
    }

    public static void dfs(int y, int x){
        if(visited[y][x] == 1){ // 사이클 발견
            safeZoneCount++;
            return;
        }
        
        if(visited[y][x] == 2){ // 이미 처리된 경로
            return;
        }

        visited[y][x] = 1; // 방문 중으로 마킹

        // 다음 위치 계산
        int ny = y, nx = x;
        char direction = map[y][x];
        
        switch(direction){
            case 'U': ny = y - 1; break;
            case 'D': ny = y + 1; break;
            case 'L': nx = x - 1; break;
            case 'R': nx = x + 1; break;
        }

        // 배열 경계 검사 (문제 조건상 항상 유효한 범위일 것이라 가정)
        if(ny >= 0 && ny < N && nx >= 0 && nx < M){
            dfs(ny, nx);
        }

        visited[y][x] = 2; // 완료로 마킹
    }
}