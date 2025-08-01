import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[][] board;
    static long max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new long[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, board);
        System.out.println(max);
    }

    public static void backTracking(int depth, long[][] curBoard){
        if(depth == 5){
            max = Math.max(max, getMaxValue(curBoard));
            return;
        }

        for(int d=0; d<4; d++){
            long[][] copyBoard = new long[N][N];
            mapCopy(curBoard, copyBoard);
            
            rotateBoard(copyBoard, d);
            backTracking(depth+1, copyBoard);
            // mapCopy(copyBoard, curBoard); 제거
        }
    }

    public static void rotateBoard(long[][] curBoard, int d){
        Stack<Long> s;
        if(d==0){ // 위
            for(int x=0; x<N; x++){
                s = new Stack<>();
                for(int y=N-1; y>=0; y--){
                    if(curBoard[y][x] != 0) { // 0이 아닌 값만 추가
                        s.add(curBoard[y][x]);
                    }
                }
                // 열 전체를 0으로 초기화
                for(int y=0; y<N; y++){
                    curBoard[y][x] = 0;
                }
                mergeBlock(d, x, s, curBoard);
            }
        }else if(d==1){ // 아래
            for(int x=0; x<N; x++){
                s = new Stack<>();
                for(int y=0; y<N; y++){
                    if(curBoard[y][x] != 0) {
                        s.add(curBoard[y][x]);
                    }
                }
                for(int y=0; y<N; y++){
                    curBoard[y][x] = 0;
                }
                mergeBlock(d, x, s, curBoard);
            }
        }else if(d==2){ // 오른쪽
            for(int y=0; y<N; y++){
                s = new Stack<>();
                for(int x=0; x<N; x++){
                    if(curBoard[y][x] != 0) {
                        s.add(curBoard[y][x]);
                    }
                }
                for(int x=0; x<N; x++){
                    curBoard[y][x] = 0;
                }
                mergeBlock(d, y, s, curBoard);
            }
        }else{ // 왼쪽
            for(int y=0; y<N; y++){
                s = new Stack<>();
                for(int x=N-1; x>=0; x--){
                    if(curBoard[y][x] != 0) {
                        s.add(curBoard[y][x]);
                    }
                }
                for(int x=0; x<N; x++){
                    curBoard[y][x] = 0;
                }
                mergeBlock(d, y, s, curBoard);
            }
        }
    }

    public static void mergeBlock(int d, int k, Stack<Long> s, long[][] curBoard){
        int startX = -1, startY = -1;
        if(d==0){
            startX = k;
            startY = 0;
        }else if(d==1){
            startX = k;
            startY = N-1;
        }else if(d==2){
            startX = N-1;
            startY = k;
        }else{
            startX = 0;
            startY = k;
        }

        while(!s.isEmpty()){
            long curBlock = s.pop();

            // 다음 블록과 합칠 수 있는지 확인
            if(!s.isEmpty() && curBlock == s.peek()){
                curBlock *= 2;
                s.pop(); // 같은 값이면 합치고 제거
            }

            // 방향에 따라 블록 배치
            if(d==0){ // 위
                if(startY<N){
                    curBoard[startY++][startX] = curBlock;
                }
            }else if(d==1){ // 아래
                if(startY>=0){
                    curBoard[startY--][startX] = curBlock;
                }
            }else if(d==2){ // 오른쪽
                if(startX>=0){
                    curBoard[startY][startX--] = curBlock;
                }
            }else{ // 왼쪽
                if(startX<N){
                    curBoard[startY][startX++] = curBlock;
                }
            }
        }
    }

    public static void mapCopy(long[][] origin, long[][] newArr){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                newArr[i][j] = origin[i][j];
            }
        }
    }

    public static long getMaxValue(long[][] b){
        long m = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                m = Math.max(m, b[i][j]);
            }
        }
        return m;
    }
}