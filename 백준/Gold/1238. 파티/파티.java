import java.util.*;
import java.io.*;

public class Main {
	static int N, M, X;
	static List<List<Node>> graph = new ArrayList<>();
	static int[] distFrom;
	static int[] distTo;

	static class Node implements Comparable<Node> {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		// N개의 숫자로 구분된 마을에 한 명의 학생이 살고 있음
		// N명의 학생이 X번 마을에 모여서 파티를 함.
		// 이 마을(X)사이에는 총 M개의 단 방향 도로들이 있고 i번쨰 길을 지나는데 Ti의 시간을 소비
		// 각각의 학생들은 파티에 참석하기 위해 걸어가서 그들의 마을로 최단시간으로 돌아와야함.
		// 도로가 단방향임. 즉 오고 가는 길이 다를 수 있음.
		// N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생을 구하기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(n1).add(new Node(n2, cost));
		}

		distFrom = new int[N + 1];
		distTo = new int[N + 1];
		Arrays.fill(distFrom, Integer.MAX_VALUE);
		Arrays.fill(distTo, Integer.MAX_VALUE);

		dijkstra(X);

		dijkstra_reverse();

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;
			max = Math.max(max, distFrom[i] + distTo[i]);
		}

		System.out.println(max);
	}

	public static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		distFrom[start] = 0;

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();

			if (curNode.cost > distFrom[curNode.idx])
				continue;

			for (Node next : graph.get(curNode.idx)) {
				int newCost = distFrom[curNode.idx] + next.cost;
				if (newCost < distFrom[next.idx]) {
					distFrom[next.idx] = newCost;
					pq.offer(new Node(next.idx, newCost));
				}
			}
		}
	}

	public static void dijkstra_reverse() {
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0));
		distTo[X] = 0;

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();

			if (curNode.cost > distTo[curNode.idx])
				continue;

			for(int i=1; i<=N; i++) { // 모든 마을 순회 
                for(Node next : graph.get(i)) { // 현재 마을에서 출발하는 간선 홧인
                    if(next.idx == curNode.idx) { // 현재 간선의 도착지가 현재 처리 중인 노드가 같으면 curNode로 가는 노드를 찾은 것
                        int newCost = distTo[curNode.idx] + next.cost;
                        if(newCost < distTo[i]) {
                            distTo[i] = newCost;
                            pq.offer(new Node(i, newCost));
                        }
                    }
                }
            }
		}
	}
}
