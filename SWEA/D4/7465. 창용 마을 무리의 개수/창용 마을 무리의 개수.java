import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
    static int N, M;
	static List<List<Integer>> abjList;
	static boolean[] visited;
	static int result;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			abjList = new ArrayList<>();
			visited = new boolean[N + 1];
			result = 0;

			for (int i = 0; i < N + 1; i++) {
				abjList.add(new ArrayList<>());
			}

			// 인접리스트 bfs 탐색

			// 인접리스트 초기화
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				// 무향 그래프 생성
				abjList.get(from).add(to);
				abjList.get(to).add(from);
			}

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i);
					result++;
				}
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
	}
    
    public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int node = q.poll();
				for(int j=0; j<abjList.get(node).size(); j++) {
					int n = abjList.get(node).get(j);
					if(!visited[n]) {
						visited[n] = true;
						q.offer(n);
					}
				}
			}
		}
	}
}