import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static List<int[]> snake = new ArrayList<>();
	static int n, k, l;
	static HashMap<Integer, String> hash = new HashMap<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 }; // 동 남 서 북

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;

		}

		l = Integer.parseInt(br.readLine());

		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			hash.put(x, c);
		}

		solve();

	}

	public static void solve() {
		int cx = 0, cy = 0;
		int time = 0;
		int d = 0;
		snake.add(new int[] { 0, 0 });

		while (true) {
			// 1. 시간재기
			time++;

			// 2. 뱀 이동하기
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			// 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
			if (isFinish(nx, ny))
				break;

			// 4. 사과가 있을 때 없을 때 처리
			if (map[nx][ny] == 1) {
				map[nx][ny] = 0;
				snake.add(new int[] { nx, ny });

			} else {
				snake.add(new int[] { nx, ny });
				snake.remove(0);
			}

			// 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
			if (hash.containsKey(time)) {
				if (hash.get(time).equals("D")) {
					d += 1;
					if (d == 4)
						d = 0;
				} else {
					d -= 1;
					if (d == -1)
						d = 3;
				}
			}

			// 6. 현재값 업데이트
			cx = nx;
			cy = ny;
			// cx cy 업데이트 위에서
		}

		System.out.println(time);
	}

	public static boolean isFinish(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
			return true;
		}

		for (int i = 0; i < snake.size(); i++) {
			int[] t = snake.get(i);
			if (nx == t[0] && ny == t[1])
				return true;
		}
		return false;
	}

}