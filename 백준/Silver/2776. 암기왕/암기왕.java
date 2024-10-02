import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Set<Integer> diary1 = new HashSet<>();
			st = new StringTokenizer(br.readLine());

			for (int n = 0; n < N; n++) {
				diary1.add(Integer.parseInt(st.nextToken()));
			}

			int M = Integer.parseInt(br.readLine());
			int[] diary2 = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				diary2[m] = Integer.parseInt(st.nextToken());
			}

			int[] result = new int[M];
			for (int i = 0; i < M; i++) {
				if (diary1.contains(diary2[i])) {
					result[i] = 1;
				} else {
					result[i] = 0;
				}
			}

			for (int i = 0; i < M; i++) {
				System.out.println(result[i]);
			}

		}
	}
}
