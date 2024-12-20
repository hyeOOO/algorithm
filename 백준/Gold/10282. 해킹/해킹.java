import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, D, C;
	static List<List<Node>> graph;
	
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
			// 비용기준 오름차순 정렬 
			return this.cost-o.cost;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			graph = new ArrayList<List<Node>>();
			
			for(int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph.get(n2).add(new Node(n1, cost));
			}
			
			dijkstra(C);
		}
	}
	
	static void dijkstra(int start) {
		int[] dist = new int[N+1]; //각 노드까지의 최소거리 배열
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(dist[curNode.node]<curNode.cost) continue;
			
			for(Node nextNode : graph.get(curNode.node)) {
				int newCost = nextNode.cost + curNode.cost;
				if(newCost<dist[nextNode.node]) {
					dist[nextNode.node] = newCost;
					pq.offer(new Node(nextNode.node, newCost));
				}
			}
		}
		
		int count = 0;
		int time = 0;
		
		for(int i=1; i<=N; i++) {
			if(dist[i]!=Integer.MAX_VALUE) {
				if(time<dist[i]) {
					time = dist[i];
				}
				count++;
			}
		}
		
		System.out.println(count+" "+time);
	}
}
