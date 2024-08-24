import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @since 2024.08.24 [Baekjoon/16234] 인구이동
 */
// 1. (0, 0)부터 (n-1, n-1)까지 특정 위치로부터 BFS로 상하좌우 탐색해서 인구수 차가 L과 R 사이에 있는지 확인
// 2. 유효하다면 해당 위치 값을 인구이동나라List에 추가하기
// 3. 특정 위치로부터 탐색이 끝나면(while(!q.isEmpty)문이 종료되었을때) 인구이동나라List의 size가 1이상일 때, 인구이동 
// 4. 인구이동나라 List의 위치값에 따른 인구수의 합 / 인구이동나라List의 size 해서 인구이동나라 List에 저장된 좌표값에 갱신
// 5. visited 안된 다음 좌표로 이동해서 1부터 반복
public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new boolean[N][N];
			boolean isMovement = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						if (bfs(i, j)) {
							isMovement = true;
						}
					}
				}
			}

			if (!isMovement)
				break;
			count++;
		}

		System.out.println(count);

	}

	public static boolean bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { y, x });
		visited[y][x] = true;
		List<int[]> movement = new ArrayList<int[]>();
		movement.add(new int[] { y, x });

		while (!q.isEmpty()) {
			int cur[] = q.poll();
			y = cur[0];
			x = cur[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx]) {
					int diff = Math.abs(map[y][x] - map[ny][nx]);
					if (diff >= L && diff <= R) {
						q.offer(new int[] { ny, nx });
						movement.add(new int[] { ny, nx });
						visited[ny][nx] = true;
					}
				}
			}

		}

		if (movement.size() > 1) {
			int total = 0;

			for (int[] country : movement) {
				total += map[country[0]][country[1]];
			}

			int people = total / movement.size();

			for (int[] country : movement) {
				map[country[0]][country[1]] = people;
			}

			return true;
		}

		return false;
	}
}
