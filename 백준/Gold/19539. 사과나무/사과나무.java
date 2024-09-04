import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int totalHeight = 0;
		int needWateringcan2Count = 0;

		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			totalHeight += height;
			needWateringcan2Count += height / 2;
		}

		if (totalHeight % 3 == 0 && needWateringcan2Count >= totalHeight / 3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}
}
