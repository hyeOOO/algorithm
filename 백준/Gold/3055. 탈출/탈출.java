import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	static Queue<int[]> stream = new LinkedList<>();
	static Queue<int[]> hedgehog = new LinkedList<>();
	static boolean[][] streamVisited;
	static boolean[][] hedgehogVisited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int goalY = -1, goalX = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		streamVisited = new boolean[R][C];
		hedgehogVisited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*') {
					stream.add(new int[] { i, j });
					streamVisited[i][j] = true;
				} else if (map[i][j] == 'S') {
					hedgehog.add(new int[] { i, j });
					hedgehogVisited[i][j] = true;
				} else if (map[i][j] == 'D') {
					goalY = i;
					goalX = j;
				}
			}
		}

		int result = bfs();
		if (result == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(result);

	}

	public static int bfs() {
		int count = 0;
		while (!hedgehog.isEmpty()) {
			count++;
			int streamSize = stream.size();
			for (int i = 0; i < streamSize; i++) {
				int[] nextStream = stream.poll();
				for (int d = 0; d < 4; d++) {
					int streamY = nextStream[0] + dy[d];
					int streamX = nextStream[1] + dx[d];

					if (streamY >= 0 && streamX >= 0 && streamY < R && streamX < C && map[streamY][streamX] != 'X'
							&& map[streamY][streamX] != 'D' && !streamVisited[streamY][streamX]) {
						map[streamY][streamX] = '*';
						streamVisited[streamY][streamX] = true;
						stream.offer(new int[] { streamY, streamX });
					}
				}
			}

			int hedgehogSize = hedgehog.size();
			for (int i = 0; i < hedgehogSize; i++) {
				int[] nextHedgehog = hedgehog.poll();

				for (int d = 0; d < 4; d++) {
					int hedgeY = nextHedgehog[0] + dy[d];
					int hedgeX = nextHedgehog[1] + dx[d];
					if (hedgeY >= 0 && hedgeX >= 0 && hedgeY < R && hedgeX < C && map[hedgeY][hedgeX] != 'X'
							&& map[hedgeY][hedgeX] != '*' && !hedgehogVisited[hedgeY][hedgeX]) {
						if (map[hedgeY][hedgeX] == 'D')
							return count;
						map[hedgeY][hedgeX] = 'S';
						hedgehogVisited[hedgeY][hedgeX] = true;
						hedgehog.offer(new int[] { hedgeY, hedgeX });
					}
				}

			}
		}
		return -1;
	}
}