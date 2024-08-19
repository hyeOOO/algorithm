/* 
	DFS, BFS 입문문제로 인터넷 참고해서 4가지 방법으로 품
	 2. DFS + 인접리스트
		 깊이 우선 탐색 : 탐색 공간에 대해 제한이 있으며, 공간 내 탐색 목표가 있을 때 유용하고 적은 공간 복잡도를 가짐.
		 인접 리스트 : 연결된 정점들을 리스트로 표현.
		 						장점 : 인접 행렬에 비해 메모리가 적어서 정점 개수가 많은 문제에 적용 및 특정 정점과 간선으로 연결된 정점들만 순회 가능
		 						단점 : 두 정점 사이에 간선 여부를 알려면 연결된 모든 정점을 순회해야함.
*/
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon2606_2 {
	static ArrayList<Integer>[] a;
	static boolean[] visit;
	static int n, m, v;
	static int count = 0;

	public static int dfs(int i) {
		visit[i] = true;
		for (int k : a[i]) {
			if (visit[k] == false) {
				count++;
				dfs(k);
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		v = 1;
		a = new ArrayList[n + 1];
		visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			a[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			a[u].add(v);
			a[v].add(u);
		}

		System.out.println(dfs(1));
	}
}
