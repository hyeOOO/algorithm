import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] DP = new int[12];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		DP[1] = 1; // 1
		DP[2] = 2; // 1+1 / 2
		DP[3] = 4; 

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			for (int j = 4; j <= num; j++) {
				DP[j] = DP[j-1] + DP[j-2] + DP[j-3];
			}

			System.out.println(DP[num]);
		}

	}
}
