import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static char[][] map;
    static int[][] visited;
    static int safezoneCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++){
            char[] cArr = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                map[i][j] = cArr[j];
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]==0){
                    dfs(i, j);
                }
            }
        }

        System.out.println(safezoneCount);
    }

    public static void dfs(int y, int x){
        if(visited[y][x]==1){
            safezoneCount++;
            return;
        }else if(visited[y][x]==2){
            return;
        }

        visited[y][x] = 1;

        int ny = y, nx = x;
        char d = map[y][x];

        switch(d){
            case 'U':ny = y-1; break;
            case 'D':ny = y+1; break;
            case 'L':nx = x-1; break;
            case 'R':nx = x+1; break;
        }

        if(ny>=0&&nx>=0&&ny<N&&nx<M){
            dfs(ny, nx);
        }

        visited[y][x] = 2;
    }
}
