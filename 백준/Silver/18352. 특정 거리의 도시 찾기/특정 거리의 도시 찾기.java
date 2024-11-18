import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Node>> graph = new ArrayList<>();
	static int[] dist;
	static class Node {
		int v;
		int cost;
		public Node(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		dist = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new Node(end, 1));
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		getDistance(X);
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		for(int i=0; i<=N; i++) {
			if(dist[i]!=Integer.MAX_VALUE && dist[i]==K) {
				sb.append(i).append('\n');
				flag=true;
			}
		}
		
		if(flag) System.out.println(sb);
		else System.out.println(-1);
	}
	
	public static void getDistance(int s) {
		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2)->o1.cost-o2.cost);
		q.offer(new Node(s,0));
		dist[s] = 0;
		
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			
			for(Node next : graph.get(curNode.v)) {
				if(dist[next.v]>dist[curNode.v]+next.cost) {
					dist[next.v] = dist[curNode.v]+next.cost;
					q.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		
	}
}
