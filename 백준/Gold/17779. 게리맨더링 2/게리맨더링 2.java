import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, totalPeople;
    static int result = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += map[i][j];
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int d1=1; d1<N; d1++){
                    for(int d2=1; d2<N; d2++){
                        if(i+d1+d2>=N) continue; // 마름모의 제일 하단 꼭짓점 유효성 체크
                        if(j-d1<0||j+d2>=N) continue; // 각 마름모의 좌측 꼭짓점과 우측 꼭짓점 유효성 체크

                        solution(i, j, d1, d2); // 상단 꼭짓점과 마름모의 변의 길이를 가지고 구역 나누기
                    }
                }
            }
        }

        System.out.println(result);
    }

    public static void solution(int y, int x, int d1, int d2){
        boolean[][] border = new boolean[N][N];
        // 마름모 변 그리기
        for(int i=0; i<=d1; i++){
            border[y+i][x-i] = true; // 상단 꼭짓점에서 좌측 꼭짓점을 향하는 변(3번 경계선)
            border[y+d2+i][x+d2-i] = true; // 좌측 꼭짓점에서 하단 꼭짓점을 향하는 변
        }

        for(int i=0; i<=d2; i++){
            border[y+i][x+i] = true; // 상단 꼭짓점에서 우측 꼭짓점을 향하는 변(2번 경계선)
            border[y+d1+i][x-d1+i] = true; // 우측 꼭짓점에서 하단 꼭짓점을 향하는 변
        }

        int[] sum = new int[5];

        // 2구역 인구수
        for(int i=0; i<y+d1; i++){
            for(int j=0; j<=x; j++){
                if(border[i][j]) break;
                sum[1] += map[i][j];
            }
        }

        // 3구역 인구수
        for(int i=0; i<=y+d2; i++){
            for(int j=N-1; j>x; j--){
                if(border[i][j]) break;
                sum[2] += map[i][j];
            }
        }

        // 4구역 인구수
        for(int i=y+d1; i<N; i++){
            for(int j=0; j<x-d1+d2; j++){
                if(border[i][j]) break;
                sum[3] += map[i][j];
            }
        }

        // 5구역 인구수
        for(int i=y+d2+1; i<N; i++){
            for(int j=N-1; j>=x-d1+d2; j--){
                if(border[i][j]) break;
                sum[4] += map[i][j];
            }
        }

        // 1구역 인구수

        sum[0] = totalPeople;
        for(int i=1; i<=4; i++){
            sum[0] -= sum[i];
        }

        Arrays.sort(sum);

        result = Math.min(result, sum[4]-sum[0]);
    }


}
