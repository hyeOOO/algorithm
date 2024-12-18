import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, start, end;
	static List<List<Node>> graph = new ArrayList<List<Node>>();
	
	static class Node{
		int idx, cost;
		
		Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(n1).add(new Node(n2, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[N+1]; //최소비용 저장 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1.cost, o2.cost)); // 비용 기준 오름차순 정렬
		
		pq.offer(new Node(start,0));
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(curNode.idx == end) {
				System.out.println(dist[curNode.idx]);
				return;
			}
			
			if(dist[curNode.idx] < curNode.cost) {
				continue;
			}
			
			for(int i=0; i<graph.get(curNode.idx).size(); i++) {
				Node nextNode = graph.get(curNode.idx).get(i);
				
				if(dist[nextNode.idx] > curNode.cost + nextNode.cost) {
					dist[nextNode.idx] = curNode.cost + nextNode.cost;
					pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
				}
			}
		}
		
		System.out.println(-1);
	}
}
