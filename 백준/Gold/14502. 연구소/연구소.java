import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static List<int[]> coord;
	static int max = Integer.MIN_VALUE;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		coord = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					coord.add(new int[] { i, j });
			}
		}

		makeComb(0, 0, new int[3]);
		System.out.println(max);
	}

	public static void makeComb(int depth, int start, int[] selected) {
		if (depth == 3) {
			makeWall(selected);
			return;
		}

		for (int i = start; i < coord.size(); i++) {
			selected[depth] = i;
			makeComb(depth + 1, i + 1, selected);
		}
	}

	public static void makeWall(int[] selected) {
		int[][] tempMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, M);
		}

		for (int index : selected) {
			int[] pos = coord.get(index);
			tempMap[pos[0]][pos[1]] = 1;
		}

		int safezone = getSafezone(tempMap);
		max = Math.max(max, safezone);
	}

	public static int getSafezone(int[][] tempMap) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempMap[i][j] == 2) {
					q.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx] && tempMap[ny][nx] == 0) {
					q.offer(new int[] { ny, nx });
					tempMap[ny][nx] = 2;
					visited[ny][nx] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j]==0) count++;
			}
		}
		
		return count;

	}
}
