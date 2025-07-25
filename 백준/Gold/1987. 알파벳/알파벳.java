import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Set<Character> alphabet = new HashSet<>();
    static int maxCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Set<Character> alphabet = new HashSet<>();

        for(int i=0; i<N; i++){
            char[] chr = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                map[i][j] = chr[j];
            }
        }

        dfs(0, 0, 1);

        System.out.println(maxCount);
    }

    public static void dfs(int y, int x, int depth){
        alphabet.add(map[y][x]);
        maxCount = Math.max(maxCount, depth);

        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny<0||nx<0||ny>=N||nx>=M) continue;
            if(!alphabet.contains(map[ny][nx])){
                dfs(ny, nx, depth+1);
                alphabet.remove(map[ny][nx]);
            }
        }
    }
}
