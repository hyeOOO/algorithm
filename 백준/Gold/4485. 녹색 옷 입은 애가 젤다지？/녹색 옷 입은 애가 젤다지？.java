import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 박혜원
 */
// 1. 맵 입력받기
// 2. 각 맵마다 이전 도둑 루피 + 현재 도둑 루피 중 최솟값만 맵에 저장
// 3. 마지막 map[n-1][n-1] 반환
public class Main {
	static class Loopy implements Comparable<Loopy> {
		int y;
		int x;
		int loopy;

		public Loopy(int y, int x, int loopy) {
			super();
			this.y = y;
			this.x = x;
			this.loopy = loopy;
		}

		@Override
		public int compareTo(Loopy o) {
			return this.loopy - o.loopy;
		}
	}

	static int N;
	static int[][] map;
	static int[][] totalLoopy;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			totalLoopy = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					totalLoopy[i][j] = Integer.MAX_VALUE;
				}
			}

			bfs(0, 0);

			System.out.println("Problem " + tc + ": " + totalLoopy[N - 1][N - 1]);
            tc++;
		}
	}

	public static void bfs(int y, int x) {
		PriorityQueue<Loopy> pq = new PriorityQueue<>();
        pq.offer(new Loopy(y, x, map[y][x]));
        totalLoopy[y][x] = map[y][x];

		while (!pq.isEmpty()) {
			Loopy current = pq.poll();

			if (current.y == N - 1 && current.x == N - 1) break; // 도착점에 도달하면 종료
			
			for (int d = 0; d < 4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
	
				if (ny >= 0 && nx >= 0 && ny < N && nx < N) {
					int newLoopy = current.loopy + map[ny][nx];
					if(newLoopy < totalLoopy[ny][nx]) {
						totalLoopy[ny][nx] = newLoopy;
						pq.offer(new Loopy(ny, nx, newLoopy));
					}
				}
			}
		}

	}
}
