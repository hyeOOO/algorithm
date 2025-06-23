import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int find = Integer.parseInt(br.readLine());

		int start = n * n;
		int[][] arr = new int[n][n];

		int y = 0, x = 0, d = 0;
		int findY = 0, findX = 0;
		while (true) {			
			if (arr[y][x] != 0 && start == 0)
				break;

			arr[y][x] = start;
			
			if (arr[y][x] == find) {
				findY = y + 1;
				findX = x + 1;
			}

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= n || nx >= n || arr[ny][nx] != 0) {
				d = (d + 1) % 4;
				ny = y + dy[d];
				nx = x + dx[d];
			}

			y = ny;
			x = nx;
			start--;
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			
			System.out.println();
		}
		System.out.printf("%d %d\n", findY, findX);
	}
}
