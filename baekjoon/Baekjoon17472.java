package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @since 2024-08-19 
 * [풀이계획] 
 * 1. bfs를 통해 섬 정보를 islands 컬렉션에 저장 
 * 2. islands끼리 연결가능한 다리에 대해 edges에 저장 
 * 2-1. islands n번째 섬과 n+1번째 섬의 좌표 원소들에 대해서 y값 혹은 x값이 같아야 일직선 다리가 됨. 
 * 2-2. y값이 같으면 x값과의 차 중에서 최소 차를, x값이 같으면 y값과의 차 중에서 최소 차가 최소 다리길이가 됨 
 * 2-3. 최소 다리길이가 2보다 작으면 다리 생성 불가 
 * 3. 여러 다리 중에서 모든 섬이 연결 가능하면서도 다리길이가 최소화 되는 다리만 선택(MST - 크루스칼 알고리즘 사용)
 * 
 * [실패/인터넷 참조]
 * 1. edges 구함에 있어 gpt와 대화
 * 2. 각 노드를 다 순회하면서 최소비용을 구하는 mst에 대한 지식 부족 이슈로 교수님 강의 청강 및 gpt와 대화
 * 3. 다리 사이에 섬이 있을 때 다리를 못놓는 경우 고려 못해서 1차 실패 -> start와 end변수를 둬서 유효성 검사 로직 추가
 * 4. 섬과 섬사이 여러 다리를 놓을 수 있는데 그 다리의 최소 길이가 1이고 그 다음 최소 길이가 2인데, 
 * 	  1만 보고 다리를 못놓는다고 판단해서 2차 실패
 * 5. 크루스칼 알고리즘을 통해 최소 비용 계산함에 있어 모든 노드가 연결되었는지 판단 로직이 누락되어 3차 실패
 */
public class Baekjoon17472 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static List<List<int[]>> islands;
	static int[] parent;

	static class Edge {
		int from;
		int to;
		int length;

		public Edge(int from, int to, int length) {
			super();
			this.from = from;
			this.to = to;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		islands = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		// 1. bfs를 통해 섬 정보를 islands 컬렉션에 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					islands.add(findIsland(i, j));
				}
			}
		}

		// 2. islands끼리 연결가능한 다리에 대해 edges에 저장
		List<Edge> edges = new ArrayList<>();
		int nodeCount = islands.size();
		for (int i = 0; i < nodeCount; i++) {
			for (int j = i + 1; j < nodeCount; j++) {
				int distance = findBridgeLength(islands.get(i), islands.get(j));
				if (distance >= 2) { // 2-3. 최소 다리길이가 2보다 작으면 다리 생성 불가
					edges.add(new Edge(i, j, distance));
				}
			}
		}

		// 3.여러 다리 중에서 모든 섬이 연결 가능하면서도 다리길이가 최소화 되는 다리만 선택
		System.out.println(kruskal(nodeCount, edges));
	}

	public static int kruskal(int nodeCount, List<Edge> edges) {
		Collections.sort(edges, (o1, o2) -> o1.length - o2.length);

		parent = new int[nodeCount];

		for (int i = 0; i < nodeCount; i++) { // makeSet
			parent[i] = i;
		}

		int mstCost = 0;
		int edgeCount = 0;

		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
	            mstCost += edge.length;
	            edgeCount++;
	        }
		}
		
		// 모든 섬이 연결되었는지 확인
	    int rootParent = findParent(0);
	    for (int i = 1; i < nodeCount; i++) {
	        if (findParent(i) != rootParent) {
	            return -1; // 모든 섬이 연결되지 않았음
	        }
	    }

		return edgeCount == nodeCount - 1 ? mstCost : -1;

	}

	public static boolean union(int n1, int n2) {
	    int parent1 = findParent(n1);
	    int parent2 = findParent(n2);

	    if (parent1 != parent2) {
	        if (parent1 < parent2) {
	            parent[parent2] = parent1;
	        } else {
	            parent[parent1] = parent2;
	        }
	        return true;
	    }
	    return false;
	}

	public static int findParent(int x) {
		if (parent[x] != x) {
			parent[x] = findParent(parent[x]);
		}
		return parent[x];
	}

//	2-1. islands n번째 섬과 n+1번째 섬의 좌표 원소들에 대해서 y값 혹은 x값이 같아야 일직선 다리가 됨.
//	2-2. y값이 같으면 x값과의 차 중에서 최소 차를, x값이 같으면 y값과의 차 중에서 최소 차가 최소 다리 길이가 됨
	public static int findBridgeLength(List<int[]> island1, List<int[]> island2) {
		int minLength = Integer.MAX_VALUE;

		for (int[] p1 : island1) {
			for (int[] p2 : island2) {
				if (p1[0] == p2[0]) {
					int start = Math.min(p1[1], p2[1]);
					int end = Math.max(p1[1], p2[1]);
					boolean hasIsland = false;

					// 다리 놓을 때 그 사이에 섬이 있는지 판단
					for (int i = start + 1; i < end; i++) {
						if (map[p1[0]][i] == 1) { 
							hasIsland = true;
							break;
						}
					}
					if (!hasIsland) {
						int length = end - start - 1;
						// 최소 길이를 비교하기 전에 다리길이가 0혹은 1이면 다리를 못만듬
						if (length < 2)
							continue;
						minLength = Math.min(minLength, length);
					}
				} else if (p1[1] == p2[1]) {
					int start = Math.min(p1[0], p2[0]);
					int end = Math.max(p1[0], p2[0]);
					boolean hasIsland = false;

					for (int i = start + 1; i < end; i++) {
						if (map[i][p1[1]] == 1) { 
							hasIsland = true;
							break;
						}
					}
					if (!hasIsland) {
						int length = end - start - 1;
						if (length < 2)
							continue;
						minLength = Math.min(minLength, length);
					}

				}
			}
		}
		return minLength == Integer.MAX_VALUE ? -1 : minLength;
	}

	public static List<int[]> findIsland(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		List<int[]> list = new ArrayList<>();
		q.offer(new int[] { y, x });
		list.add(new int[] { y, x });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			y = cur[0];
			x = cur[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
					list.add(new int[] { ny, nx });
				}
			}
		}
		return list;
	}
}
