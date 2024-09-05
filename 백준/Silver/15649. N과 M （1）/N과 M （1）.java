import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}

		Arrays.sort(arr);
		bfs(0, new int[M], new boolean[N]);
		
		System.out.println(sb);
	}

	public static void bfs(int depth, int[] select, boolean[] visited) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				select[depth] = arr[i];
				bfs(depth + 1, select, visited);
				visited[i] = false;
			}
		}
	}
}
