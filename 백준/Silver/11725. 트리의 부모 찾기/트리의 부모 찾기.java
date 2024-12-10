import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		parent = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			// 양방향으로 추가
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		// 루트는 1
		bfs(1);
		
		for(int i=2; i<=N; i++) {
			System.out.println(parent[i]);
		}
	}
	
	public static void bfs(int p) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(p);
		visited[p] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// 현재 노드와 이어진 노드 구하기
			for(int next : graph.get(cur)) {
				
				// 해당 노드를 방문한 적 없으면 
				if(!visited[next]) {
					// 해당 노드의 부모는 현재 노드
					parent[next] = cur;
					// 자식노드의 자손후보를 큐에 넣기
					q.offer(next);
					// 방문처리
					visited[next] = true;
				}
			}
		}
	}
}
