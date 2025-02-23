import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    static boolean[][] visited;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        board = new char[N][M];

        for(int i=0; i<N; i++){
            board[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited = new boolean[N][M];
                if(dfs(i,j, i, j, 0)){
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    static boolean dfs(int y, int x, int startY, int startX, int depth){
        //시작점으로 돌아왔고, 깊이가 4 이상이면 true
        if(y==startY && x==startX && depth>=4){
            return true;
        }

        visited[y][x] = true;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

            if (board[ny][nx] != board[y][x]) continue;

            // 방문하지 않았거나, 시작점으로 돌아오는 경우
            if (!visited[ny][nx] || (nx == startX && ny == startY && depth >= 3)) {
                if (dfs(ny, nx, startY, startX, depth + 1)) return true;
            }
        }

        return false;
    }
}