import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] arr = new int[3][2];

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		int result = ccw(arr[0][0], arr[0][1], arr[1][0], arr[1][1], arr[2][0], arr[2][1]);

		System.out.println(result);
	}

	public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		int temp = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);

		if (temp > 0)
			return 1;
		else if (temp < 0)
			return -1;
		else
			return 0;
	}
}
