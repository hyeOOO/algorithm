import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] arr;
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = Math.min(N, M) / 2; //돌아가는 레일 수
		for(int i=0; i<R; i++) { //회전 횟수만큼 반복
			for(int j=0; j<count; j++) { //레일 마다 회전
				int start = arr[j][j]; // 각 레일의 (0,0) 시작 값 백업
				
				for(int k=j+1; k<M-j; k++) { // 사각형 중 천장값 회전
					arr[j][k-1] = arr[j][k];
				}
				
				for(int k=j+1; k<N-j; k++) { // 사각형 중 왼쪽벽값 회전
					arr[k-1][M-1-j] = arr[k][M-1-j];
				}
				
				for(int k=M-2-j; k>=j; k--) { // 사각형 중 바닥값 회전
					arr[N-1-j][k+1] = arr[N-1-j][k];
				}
				
				for(int k=N-2-j; k>=j; k--) { // 사각형 중 오른쪽벽값 회전
					arr[k+1][j] = arr[k][j];
				}
					
				arr[j+1][j] = start; // 시작 값 복원
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
    }

  
}