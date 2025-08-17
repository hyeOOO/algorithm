import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N = 4;
    static int maxScore = 0;
    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static int[] dx = {0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] fish = new int[4][4];
        int[][] dir = new int[4][4];

        for(int i=0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                fish[i][j] = Integer.parseInt(st.nextToken());
                dir[i][j] = Integer.parseInt(st.nextToken())-1;
            }
        }

        boolean[] fishList = new boolean[17];
        for(int i=1; i<=16; i++) fishList[i] = true;

        backtracking(fish, dir, fishList, 0, 0, 0);
        System.out.println(maxScore);
    }

    public static void backtracking(int[][] fish, int[][] dir, boolean[] fishList, int sharkY, int sharkX, int score){
        // 배열 복사 - 모든 배열을 복사본으로 작업
        int[][] newFish = arrayCopy(fish);
        int[][] newDir = arrayCopy(dir);
        boolean[] newFishList = fishList.clone();

        // 상어가 물고기 잡아먹기 - 복사본에서 작업
        int eatFish = newFish[sharkY][sharkX];
        int sd = newDir[sharkY][sharkX];
        score += eatFish;
        newFishList[eatFish] = false; // 복사본 fishList 수정

        newFish[sharkY][sharkX] = 0; // 복사본 fish 수정
        newDir[sharkY][sharkX] = 0;  // 복사본 dir 수정

        maxScore = Math.max(maxScore, score);

        // 물고기 이동 - 복사본으로 작업
        moveFish(newFish, newDir, newFishList, sharkY, sharkX);

        // 상어 이동
        int nextSY = sharkY;
        int nextSX = sharkX;
        while(true){
            nextSY += dy[sd];
            nextSX += dx[sd];

            if(nextSY < 0 || nextSX < 0 || nextSY >= N || nextSX >= N) break;
            if(newFish[nextSY][nextSX] == 0) continue; // 물고기가 없으면 지나감

            backtracking(newFish, newDir, newFishList, nextSY, nextSX, score);
        }
    }

    public static void move(int y, int x, int[][] fish, int[][] dir, int sy, int sx){
        int d = dir[y][x];
        int changeCount = 0; // 0부터 시작

        while(changeCount < 8) { // 8번 시도
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 이동 가능한 경우
            if(ny >= 0 && nx >= 0 && ny < N && nx < N && !(ny == sy && nx == sx)) {
                // 물고기 위치 교환
                int temp = fish[ny][nx];
                fish[ny][nx] = fish[y][x];
                fish[y][x] = temp;

                // 방향 교환
                int temp2 = dir[ny][nx];
                dir[ny][nx] = d;
                dir[y][x] = temp2;
                return;
            }

            d = (d + 1) % 8;
            changeCount++;
        }
    }

    public static void moveFish(int[][] fish, int[][] dir, boolean[] fishList, int sharkY, int sharkX){
        for(int i = 1; i <= 16; i++){
            if(!fishList[i]) continue; // 죽은 물고기는 움직임 x

            // 물고기 찾기
            boolean found = false;
            for(int y = 0; y < 4 && !found; y++){
                for(int x = 0; x < 4; x++){
                    if(i == fish[y][x]){
                        move(y, x, fish, dir, sharkY, sharkX);
                        found = true;
                        break;
                    }
                }
            }
        }
    }

    public static int[][] arrayCopy(int[][] origin){
        int[][] newArr = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newArr[i][j] = origin[i][j];
            }
        }
        return newArr;
    }
}