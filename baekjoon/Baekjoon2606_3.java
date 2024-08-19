/* 
	DFS, BFS 입문문제로 인터넷 참고해서 4가지 방법으로 품
	 3. BFS + 인접행렬
		 너비 우선 탐색 : 최단 거리, 최단 시간 등 목표 상태에 도달할 수 있는 가장 빠른 경로 탐색. 
		 							초기 상태와 가까운 상태부터 탐색.
		 							탐색 공간의 깊이 제한 없을 때 사용. -> 그래서 공간 복잡도가 높음(메모리 효율성 낮음)
		 							하지만 안정성 높음
		 인접 행렬 : 그래프를 정사각형 모양의 2차원 배열로 나타냄. 각 인덱스는 정점을 의미하고, 원소는 각 차원의 인덱스에 해당하는 정점 사이에 있는 간선의 여부
		 					단점 : 정점 개수가 너무 많으면 배열 할당에 어려움이 있음. 또한 한 정점과 연결된 정점을 찾으려면 2차원 배열에서 하나의 열을 모두 순회해야함.
*/
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2606_3 {
	static int[][] map;
	static boolean[] visit; // 방문 여부 배열
	static int n, m, v; // n - 정점, m - 간선, v - 탐색 번호
	static int count = 0;

	public static int bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visit[i] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int k = 1; k <= n; k++) {
				if (map[temp][k] == 1 && visit[k] == false) {
					q.offer(k);
					visit[k] = true;
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
		map = new int[n + 1][n + 1]; // [0][0]은 버리기 때문에 +1
		visit = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = map[b][a] = 1;
		}

		System.out.println(bfs(1));
	}
}
