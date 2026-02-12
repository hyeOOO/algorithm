import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] tempMap;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulation());
    }

    public static void dfs(int sy, int sx){
        visited[sy][sx] = true;

        for(int d=0; d<4; d++){
            int ny = sy+dy[d];
            int nx = sx+dx[d];

            if(ny<0||nx<0||ny>=N||nx>=M) continue;
            if(visited[ny][nx]||map[ny][nx]<1) continue;

            dfs(ny, nx);
        }
    }

    public static int getEmptyCount(int y, int x){
        int count = 0;
        for(int d=0; d<4; d++){
            int ny = y+dy[d];
            int nx = x+dx[d];

            if(ny<0||nx<0||ny>=N||nx>=M) continue;
            if(map[ny][nx]==0) count++;
        }

        return count;
    }

    public static int simulation(){
        int year = 0;
        while(true) {
            // 섬 개수 체크
            int islandCount = 0;
            visited = new boolean[N][M];

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        islandCount++;
                        dfs(i, j);
                    }
                }
            }

            if (islandCount >= 2) return year;
            if(islandCount == 0) return 0;

            // 빙하 녹이기
            tempMap = new int[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] > 0) {
                        int emptyCount = getEmptyCount(i, j);
                        tempMap[i][j] = emptyCount;
                    }
                }
            }

            for(int i=1; i<N-1; i++){
                for(int j=1; j<M-1; j++){
                    map[i][j]-=tempMap[i][j];

                    if(map[i][j]<0) map[i][j]=0;
                }
            }

            year++;
        }
    }
}
