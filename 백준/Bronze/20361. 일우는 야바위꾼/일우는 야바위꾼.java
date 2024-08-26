import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, X, K;
	static int[] cup;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cup = new int[N];

		int candyCup = X;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			if (c1 == candyCup) {
				candyCup = c2;
			} else if (c2 == candyCup) {
				candyCup = c1;
			}
		}
		System.out.printf("%d\n", candyCup);
	}
}
