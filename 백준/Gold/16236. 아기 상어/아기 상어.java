import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @Date 2024.09.05
 * @Note
 * [구현 방식]
 * 현재 상어 위치에서 물고기를 더이상 못먹을때까지 제일 가까운 물고기를 잡아먹는 BFS+시뮬레이션
 * [구현 과정]
 * 1. 맵 정보 입력받고 상어의 시작위치 찾기(상어 시작 위치또한 탐색 대상이 되기 때문에 0으로 초기화 필요)
 * 2. 시작지점으로부터 물고기를 더이상 못먹을때까지 BFS하기
 * 3. 물고기 잡아먹는데도 순위가 있음(제일 가까운거->위쪽->왼쪽 순) => 우선순위큐 사용 
 * 4. 특정 위치의 물고기를 잡아먹을때(우선순위큐로 인해 위치적으로는 제일 최적화된 물고기가 선택된거임)
 * 		4-1. 물고기의 사이즈가 상어 사이즈보다 같거나 크면 못잡아먹음
 * 		4-2. 작다면 바로 잡아먹고 상어레벨과 상어크기 변동 시키고 해당 물고기의 위치를 상어 위치로 갱신시키고 물고기까지의 거리를 시간에 더하기
 */
public class Main {
	static class Shark implements Comparable<Shark> {
		int y;
		int x;
		int step;

		public Shark(int y, int x, int step) {
			super();
			this.y = y;
			this.x = x;
			this.step = step;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.step != o.step)
				return Integer.compare(this.step, o.step);
			if (this.y != o.y)
				return Integer.compare(this.y, o.y);
			return Integer.compare(this.x, o.x);
		}

	}

	static int N, sharkSize, sharkLevel, result;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sharkSize = 2;
		int startY = 0;
		int startX = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					startY = i;
					startX = j;
					map[i][j] = 0; // 상어출발지도 이동거리 대상이 됨
				}
			}
		}

		bfs(startY, startX);

		System.out.println(result);
	}

	public static void bfs(int y, int x) {
		// 더이상 먹을 물고기가 없을 때까지 돌아다녀야함
		while (true) {
			boolean isEat = false; //상어가 물고기를 먹었는지 판단
			PriorityQueue<Shark> pq = new PriorityQueue<>(); // 상어가 물고기를 만날 때까지의 움직임
			boolean[][] visited = new boolean[N][N];
			int[] targetFish = null;

			pq.offer(new Shark(y, x, 0));
			visited[y][x] = true;

			while (!pq.isEmpty()) {
				Shark curShark = pq.poll();
				int curY = curShark.y;
				int curX = curShark.x;
				int curStep = curShark.step;

				// 물고기를 잡아먹을 수 있을 때
				if (map[curY][curX] != 0 && map[curY][curX] < sharkSize) {

					isEat = true;
					map[curY][curX] = 0;
					targetFish = new int[3];
					targetFish[0] = curY;
					targetFish[1] = curX;
					targetFish[2] = curStep;

					break;

				}

				for (int d = 0; d < 4; d++) {
					int ny = curY + dy[d];
					int nx = curX + dx[d];

					if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx]) {
						if (map[ny][nx] <= sharkSize) {
                            visited[ny][nx] = true;
                            pq.offer(new Shark(ny, nx, curStep + 1));
                        }
					}
				}
			}

			if (!isEat)
				break;
			sharkLevel++;
			if (sharkLevel == sharkSize) {
				sharkSize++;
				sharkLevel = 0;
			}
			y = targetFish[0];
			x = targetFish[1];
			result += targetFish[2];
		}
	}
}
