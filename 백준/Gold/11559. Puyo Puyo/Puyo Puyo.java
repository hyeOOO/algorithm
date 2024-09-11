import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static List<int[]> popList;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		popList = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while (true) {
			visited = new boolean[12][6];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						List<int[]> tempPopList = new ArrayList<>();
						visited[i][j] = true;
						tempPopList.add(new int[] { i, j });
						dfs(i, j, map[i][j], tempPopList);

						if (tempPopList.size() >= 4) {
							popList.addAll(tempPopList);
						}
					}
				}
			}

			if (popList.isEmpty())
				break;

			popBlock(popList);
			popList.clear();
			result++;
		}

		System.out.println(result);
	}

	public static void dfs(int y, int x, char color, List<int[]> temp) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny >= 0 && nx >= 0 && ny < 12 && nx < 6 && !visited[ny][nx]) {
				if (map[ny][nx] == color) {
					visited[ny][nx] = true;
					temp.add(new int[] { ny, nx });
					dfs(ny, nx, color, temp);
				}
			}
		}
	}

	public static void popBlock(List<int[]> blockList) {
		for (int[] coord : blockList) {
			map[coord[0]][coord[1]] = '.';
		}
		pullBlock();
	}

	public static void pullBlock() {
		for (int i = 0; i < 6; i++) {
			Queue<Character> q = new LinkedList<>();

			for (int j = 11; j >= 0; j--) {
				if (map[j][i] != '.')
					q.offer(map[j][i]);
			}

			for (int j = 11; j >= 0; j--) {
				map[j][i] = '.';
			}

			int idx = 11;
			// 블럭을 내려보내자
			while (!q.isEmpty()) {
				map[idx--][i] = q.poll();
			}
		}
	}
}
