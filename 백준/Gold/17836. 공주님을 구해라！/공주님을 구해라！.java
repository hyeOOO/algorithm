import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static int[][] map;
	static int[][] valueMap;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static List<int[]> gram = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		valueMap = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				valueMap[i][j] = Integer.MAX_VALUE; // valueMap 초기화
			}
		}

		bfs(0, 0);
		
		System.out.println(getAnswer());
	}

	public static void bfs(int y, int x) {
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[] { y, x });
	    valueMap[y][x] = 0;
	    
	    while (!q.isEmpty()) {
	        int[] cur = q.poll();
	        y = cur[0];
	        x = cur[1];
	        
	        if (y == N-1 && x == M-1) break;  // 목적지 도달 시 종료
	        
	        for (int d = 0; d < 4; d++) {
	            int ny = y + dy[d];
	            int nx = x + dx[d];
	            if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] != 1) {
	                if (valueMap[ny][nx] > valueMap[y][x] + 1) {
	                    valueMap[ny][nx] = valueMap[y][x] + 1;
	                    q.offer(new int[] { ny, nx });
	                    if (map[ny][nx] == 2) {
	                        gram.add(new int[] {ny, nx});
	                    }
	                }
	            }
	        }
	    }
	}
	
	public static String getAnswer() {
	    int withoutGram = valueMap[N-1][M-1];
	    int withGram = Integer.MAX_VALUE;
	    
	    for (int[] g : gram) {
	        int gramUse = valueMap[g[0]][g[1]] + Math.abs(N-1-g[0]) + Math.abs(M-1-g[1]);
	        withGram = Math.min(withGram, gramUse);
	    }
	    
	    int minTime = Math.min(withoutGram, withGram);
	    
	    if (minTime <= T) {
	        return Integer.toString(minTime);
	    } else {
	        return "Fail";
	    }
	}
}
