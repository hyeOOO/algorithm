
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N;
	static int[] weight;
	static int result = 0;
	static int[] select;
	static boolean[] visited;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			weight = new int[N];

			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}

			result = 0;
			select = new int[N];
			visited = new boolean[N];
			makePerm(0);
			
			System.out.printf("#%d %d\n", t, result);
		}
        
    }
    
    public static void makePerm(int depth) {
		if (depth == N) {
			makeSubSet(0, 0, 0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				select[depth] = weight[i];
				makePerm(depth + 1);
				visited[i] = false;
			}
		}
	}

	public static void makeSubSet(int depth, int left, int right) {
		if (left < right)
			return;
		if (depth == N) {
			result++;
			return;
		}

		makeSubSet(depth + 1, left + select[depth], right);
		makeSubSet(depth + 1, left, right + select[depth]);

	}
}