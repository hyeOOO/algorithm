import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int[] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		dist = new int[N+1];
		graph = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		bfs(1);
		
		for(int d : dist) {
			if(d<=2) answer++;
		}
		
		System.out.println(answer-1);
	}
	
	public static void bfs(int n) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {n, 0});
		dist[n] = 0;
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int next : graph.get(cur[0])) {
				if(!visited[next]) {
					visited[next] = true;
					dist[next] = Math.min(dist[next], cur[1]+1);
					q.offer(new int[] {next, cur[1]+1});
				}
			}
		}
	}

}
