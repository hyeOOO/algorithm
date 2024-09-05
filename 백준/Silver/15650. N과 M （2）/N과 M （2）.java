import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}

		dfs(0, new int[M], 0);

		System.out.println(sb);
	}

	public static void dfs(int depth, int[] select, int start) {
		if (depth == M) {
			for (int s : select) {
				sb.append(s).append(' ');
			}
			sb.append('\n');

			return;
		}

		for (int i = start; i < N; i++) {
			select[depth] = arr[i];
			dfs(depth + 1, select, i + 1);
		}
	}
}
