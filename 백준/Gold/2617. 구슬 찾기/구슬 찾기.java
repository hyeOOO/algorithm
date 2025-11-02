import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a][b] = 1; // a가 b보다 무거움
			graph[b][a] = -1; // b가 a보다 가벼움
		}

		// 플로이드-워셜: k가 가장 바깥 루프!
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(graph[i][k]==1 && graph[k][j]==1) { // i>k인데, k>j일때,
						graph[i][j] = 1; // i>j
						graph[j][i] = -1; // j<i
					}

					if(graph[i][k]==-1 && graph[k][j]==-1) { // i<k인데, k<j일때,
						graph[i][j] = -1; // i<j
						graph[j][i] = 1; // j>i
					}
				}
			}
		}

		int mid = (N+1)/2; // 중간 구슬 위치
		int answer = 0; // 중간이 될 수 없는 구슬의 수
		for(int i=1; i<=N; i++) {
			int heavier = 0; // 자신보다 무거운 구슬 수
			int lighter = 0; // 자신보다 가벼운 구슬 수

			for(int j=1; j<=N; j++) {
				if(graph[i][j]==-1) heavier++; // i<j인 경우(j가 더 무거움)
				if(graph[i][j]==1) lighter++; // i>j인 경우 (j가 더 가벼움)
			}

			if(heavier>=mid || lighter>=mid) {
				answer++;
			}
 		}

		System.out.println(answer);

	}
}