package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea5653 {
	static int N, M, K;
	static int sizeN, sizeM;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static PriorityQueue<Cell> pq;

	static class Cell {
		int y;
		int x;
		int originVital;
		int curVital;

		public Cell(int y, int x, int originVital, int curVital) {
			super();
			this.y = y;
			this.x = x;
			this.originVital = originVital;
			this.curVital = curVital;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			sizeN = N + K ;
			sizeM = M + K;
			int start = K / 2;

			visited = new boolean[sizeN][sizeM];
			pq = new PriorityQueue<>((c1, c2) -> c2.originVital - c1.originVital);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int vital = Integer.parseInt(st.nextToken());
					if(vital!=0) {
						pq.offer(new Cell(start + i, start + j, vital, vital));
						visited[start + i][start + j] = true;
					}
					
				}
			}

			simulation();
			
			System.out.printf("#%d %d\n", t, pq.size());
		}
	}

	public static void simulation() {
		Queue<Cell> q = new LinkedList<>(); // 배양 후보 임시 저장

		for (int k = 0; k < K; k++) {
			while (!pq.isEmpty()) {
				Cell c = pq.poll();

				c.curVital--;
				if (c.curVital < 0) {
					for (int d = 0; d < 4; d++) {
						int ny = c.y + dy[d];
						int nx = c.x + dx[d];
						if (visited[ny][nx])
							continue;
						visited[ny][nx] = true;
						q.offer(new Cell(ny, nx, c.originVital, c.originVital));
					}
				}

				if (c.curVital + c.originVital == 0)
					continue; // 죽은세포
				q.offer(c); //아직 죽지않고 배양 가능하면 배양

			}

			while (!q.isEmpty())
				pq.offer(q.poll());
		}
	}
}
