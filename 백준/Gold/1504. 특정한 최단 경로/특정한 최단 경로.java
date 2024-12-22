import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, E;
	static int require1, require2;
	static List<List<Node>> graph;
	static int maxValue = 200_000_000/2;
	
	static class Node implements Comparable<Node>{
		int node;
		int cost;
		public Node(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<List<Node>>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(n1).add(new Node(n2, cost));
			graph.get(n2).add(new Node(n1,cost)); //양방향인줄 몰랐다..
		}
		
		st = new StringTokenizer(br.readLine());
		require1 = Integer.parseInt(st.nextToken());
		require2 = Integer.parseInt(st.nextToken());
		
		int result = findShortPath(1, N, require1, require2);
		
		System.out.println(result>=maxValue?-1:result);
	}
	
	static int findShortPath(int start, int end, int v1, int v2) {
		int path1 = dijkstra(start, v1) + dijkstra(v1, v2) + dijkstra(v2, end);
		int path2 = dijkstra(start, v2) + dijkstra(v2, v1) + dijkstra(v1, end);
		
		if(path1 >= maxValue && path2 >= maxValue) return maxValue;
		return Math.min(path1, path2);
	}
	
	static int dijkstra (int start, int end) {
		int[] dist = new int[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//Integer.MAX_VALUE를 쓰게 된다면, findShortPath의 path를 구하는 덧셈에서 오버플로우 발생
		Arrays.fill(dist, maxValue); 
		dist[start] = 0;
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(curNode.cost>dist[curNode.node]) continue;
			if(curNode.node == end) return dist[end];
			
			for(Node next : graph.get(curNode.node)) {
				int newCost = curNode.cost + next.cost;			
				if(newCost<dist[next.node]) {
					dist[next.node] = newCost;
					pq.offer(new Node(next.node, newCost));
				}
			}
		}
		
		return dist[end];
	}
}
