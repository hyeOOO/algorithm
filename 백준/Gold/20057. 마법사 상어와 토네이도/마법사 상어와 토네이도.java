import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy = {0, 1,0,-1};
    static int[] dx = {-1,0,1,0};
    static boolean[][] visited;
    static int score = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int step = 1;
        int cy = N/2;
        int cx = N/2;

        visited[cy][cx] = true;
        while( true ){
            for(int d=0; d<4; d++){
                for(int s = 0; s<step; s++){
                    cy += dy[d];
                    cx += dx[d];

                    if(cy<0||cx<0||cy>=N||cx>=N) continue;

                    spreadSand(cy, cx, cy+dy[d], cx+dx[d], d);

                    if (cy == 0 && cx == 0) {
                        System.out.println(score);
                        return;
                    }

                }

                if(d==1||d==3) step++; // 상/하 움직임 이후에는 스텝이 늘어남
            }
        }
    }

    public static void spreadSand(int y, int x, int aY, int aX, int d){
        // System.out.println("("+y+","+x+")");
        if(map[y][x] < 10){ // 10미만은.. 그렇게 됐다.
            if(aY<0||aX<0||aY>=N||aX>=N) {
                score += map[y][x];
            }else{
                map[aY][aX] += map[y][x];
            }

            map[y][x] = 0;
            return;
        }

        int addSand7 = (int) (0.07*map[y][x]);
        int addSand1 = (int) (0.01*map[y][x]);
        int addSand2 = (int) (0.02*map[y][x]);
        int addSand5 = (int) (0.05*map[y][x]);
        int addSand10 = (int) (0.1*map[y][x]);
        int amount = 0;

        if(d==0){ // <-
            if(y-1<0) score+=addSand7;
            else map[y-1][x]+=addSand7;

            if(y-2<0) score+=addSand2;
            else map[y-2][x]+=addSand2;

            if(y+1>=N) score+=addSand7;
            else map[y+1][x]+=addSand7;

            if(y+2>=N) score+=addSand2;
            else map[y+2][x]+=addSand2;

            if(y-1<0||x-1<0) score+=addSand10;
            else map[y-1][x-1]+=addSand10;

            if(y+1>=N||x-1<0) score+=addSand10;
            else map[y+1][x-1]+=addSand10;

            if(x-2<0) score+=addSand5;
            else map[y][x-2]+=addSand5;

            if(x+1>=N||y-1<0) score+=addSand1;
            else map[y-1][x+1] += addSand1;

            if(x+1>=N||y+1>=N) score+=addSand1;
            else map[y+1][x+1] += addSand1;
        }else if(d==1){ // 아래
            if(x-1<0) score+=addSand7;
            else map[y][x-1]+=addSand7;

            if(x-2<0) score+=addSand2;
            else map[y][x-2]+=addSand2;

            if(x+1>=N) score+=addSand7;
            else map[y][x+1]+=addSand7;

            if(x+2>=N) score+=addSand2;
            else map[y][x+2]+=addSand2;

            if(y+1>=N||x+1>=N) score+=addSand10;
            else map[y+1][x+1]+=addSand10;

            if(y+1>=N||x-1<0) score+=addSand10;
            else map[y+1][x-1]+=addSand10;

            if(y+2>=N) score+=addSand5;
            else map[y+2][x]+=addSand5;

            if(x-1<0||y-1<0) score+=addSand1;
            else map[y-1][x-1] += addSand1;

            if(x+1>=N||y-1<0) score+=addSand1;
            else map[y-1][x+1] += addSand1;
        }else if(d==2){ // ->
            if(y-1<0) score+=addSand7;
            else map[y-1][x]+=addSand7;

            if(y-2<0) score+=addSand2;
            else map[y-2][x]+=addSand2;

            if(y+1>=N) score+=addSand7;
            else map[y+1][x]+=addSand7;

            if(y+2>=N) score+=addSand2;
            else map[y+2][x]+=addSand2;

            if(y-1<0||x+1>=N) score+=addSand10;
            else map[y-1][x+1]+=addSand10;

            if(y+1>=N||x+1>=N) score+=addSand10;
            else map[y+1][x+1]+=addSand10;

            if(x+2>=N) score+=addSand5;
            else map[y][x+2]+=addSand5;

            if(x-1<0||y-1<0) score+=addSand1;
            else map[y-1][x-1] += addSand1;

            if(x-1<0||y+1>=N) score+=addSand1;
            else map[y+1][x-1] += addSand1;
        }else{ // 위
            if(x-1<0) score+=addSand7;
            else map[y][x-1]+=addSand7;

            if(x-2<0) score+=addSand2;
            else map[y][x-2]+=addSand2;

            if(x+1>=N) score+=addSand7;
            else map[y][x+1]+=addSand7;

            if(x+2>=N) score+=addSand2;
            else map[y][x+2]+=addSand2;

            if(y-1<0||x+1>=N) score+=addSand10;
            else map[y-1][x+1]+=addSand10;

            if(y-1<0||x-1<0) score+=addSand10;
            else map[y-1][x-1]+=addSand10;

            if(y-2<0) score+=addSand5;
            else map[y-2][x]+=addSand5;

            if(x-1<0||y+1>=N) score+=addSand1;
            else map[y+1][x-1] += addSand1;

            if(x+1>=N||y+1>=N) score+=addSand1;
            else map[y+1][x+1] += addSand1;
        }

        amount = (map[y][x]-(addSand1*2+addSand2*2+addSand5+addSand7*2+addSand10*2));

        if(aY<0||aX<0||aY>=N||aX>=N) score+=amount;
        else map[aY][aX] += amount;
    }
}
