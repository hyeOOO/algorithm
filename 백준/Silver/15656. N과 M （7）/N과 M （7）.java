import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		
		dfs(0, new int[M], 0);
		
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int[] select, int prev) {
		if(depth==M) {
			for(int s : select) {
				sb.append(s).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i=prev; i<N; i++) {
			select[depth] = arr[i];
			dfs(depth+1, select, prev);
		}
	}
}
