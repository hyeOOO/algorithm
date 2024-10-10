import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		int[][] prefix = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		 // 누적 합 배열 생성
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefix[i][j] = map[i][j]
                              + prefix[i - 1][j]
                              + prefix[i][j - 1]
                              - prefix[i - 1][j - 1];
            }
        }
        
     // 쿼리 처리
        for (int q = 0; q < M; q++) {
        	st = new StringTokenizer(br.readLine());
        	
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            int result = prefix[x2][y2]
                       - prefix[x1 - 1][y2]
                       - prefix[x2][y1 - 1]
                       + prefix[x1 - 1][y1 - 1];
            
            System.out.println(result);
        }
	}
}
