/* 
	DFS, BFS 입문문제로 인터넷 참고해서 4가지 방법으로 품
	 4. BFS + 인접리스트
		 너비 우선 탐색 : 최단 거리, 최단 시간 등 목표 상태에 도달할 수 있는 가장 빠른 경로 탐색. 
		 							초기 상태와 가까운 상태부터 탐색.
		 							탐색 공간의 깊이 제한 없을 때 사용. -> 그래서 공간 복잡도가 높음(메모리 효율성 낮음)
		 							하지만 안정성 높음
		 인접 리스트 : 연결된 정점들을 리스트로 표현.
		 						장점 : 인접 행렬에 비해 메모리가 적어서 정점 개수가 많은 문제에 적용 및 특정 정점과 간선으로 연결된 정점들만 순회 가능
		 						단점 : 두 정점 사이에 간선 여부를 알려면 연결된 모든 정점을 순회해야함.
*/
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2606_4 {
	static ArrayList<Integer>[] a;
	static boolean[] visit;
	static int n, m, v;
	static int count = 0;

	public static int bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visit[i] = true;

		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int k = 0; k < a[temp].size(); k++) {
				int next = a[temp].get(k);
				if (!visit[next]) {
					q.offer(next);
					visit[next] = true;
					count++;
				}
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

		System.out.println(bfs(1));
	}
}
