import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static List<List<Integer>> friends = new ArrayList<>();
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Point implements Comparable<Point>{
        int friendCount;
        int emptyCount;
        int y;
        int x;

        Point(int fCount, int eCount, int y, int x){
            this.friendCount = fCount;
            this.emptyCount = eCount;
            this.y = y;
            this.x = x;
        }

        public int compareTo(Point p){
            if(this.friendCount!=p.friendCount){
                return p.friendCount-this.friendCount;
            }
            if(this.emptyCount!=p.emptyCount){
                return p.emptyCount-this.emptyCount;
            }
            if(this.y!=p.y){
                return this.y-p.y;
            }
            return this.x-p.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<=N*N; i++){
            friends.add(new ArrayList<>());
        }

        for(int i=0; i<N*N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++){
                int f = Integer.parseInt(st.nextToken());
                friends.get(me).add(f);
            }

            seatDown(me);
        }

        System.out.println(calcScore());
    }

    public static int calcScore(){
        int score = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int friendCount = findFriend(i, j, map[i][j]);

                score += Math.pow(10, friendCount-1);
            }
        }

        return score;
    }

    public static void seatDown(int target){
        List<Point> points = new ArrayList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]!=0) continue; // 이미 자리에 누가 앉아있으면 스킵
                int friendCount = findFriend(i, j, target); // 인접한 칸의 친구 찾기
                int emptyCount = findEmpty(i, j); // 인접한 칸의 빈 칸 찾기

                points.add(new Point(friendCount, emptyCount, i, j));
            }
        }

        Collections.sort(points);

        Point p = points.get(0);

        map[p.y][p.x] = target;
    }

    public static int findEmpty(int y, int x){
        int count = 0;
        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny<0||nx<0||ny>=N||nx>=N) continue;

            if(map[ny][nx]==0) count++;
        }

        return count;
    }

    public static int findFriend(int y, int x, int t){
        List<Integer> friendList = friends.get(t);
        int count = 0;
        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny<0||nx<0||ny>=N||nx>=N) continue;

            if(friendList.contains(map[ny][nx])) count++;
        }

        return count;
    }

}
