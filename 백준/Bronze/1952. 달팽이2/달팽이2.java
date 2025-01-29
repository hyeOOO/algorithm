import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];

		int y = 0, x = 0, d = 0, count = 0;

		while (true) {
			if (visited[y][x])
				break;
			visited[y][x] = true;

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) {
				d = (d + 1) % 4;
				count++;
			}
			
			y = y + dy[d];
			x = x + dx[d];
		}
		
		System.out.println(count-1);

	}
}
