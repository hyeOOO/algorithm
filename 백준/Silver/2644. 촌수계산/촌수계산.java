import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M, start, end; // N=사람 수, start-end=촌수 계산 대상, M=관계 수
	static List<List<Integer>> graph = new ArrayList<>(); // 자식 노드를 담기 위한 리스트
	static boolean[] visited;
	static int result = -1;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>()); // 자식 리스트 초기화
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			graph.get(child).add(parent); // 노드의 부모 저장
			graph.get(parent).add(child); // 노드의 자식들 추가
		}
		
		dfs(start, end, 0);
		System.out.println(result);
	}
	
	public static void dfs(int current, int end, int count) {
		if(current==end) {
			result = count;
			return;
		}
		
		visited[current] = true;
		
		for(int next : graph.get(current)) {
			if(!visited[next]) {
				dfs(next, end, count+1);
			}
		}
	}
}
