import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static List<List<Node>> graph= new ArrayList<>();
	static int[] subject, indegree;
	
	static class Node{
		int idx;
		int step;
		public Node(int idx, int step) {
			this.idx = idx;
			this.step = step;
		}
		public int getStep() {
			return step;
		}
		public void setStep(int step) {
			this.step = step;
		}		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			graph.get(n1).add(new Node(n2,1));
		}
		
		subject = new int[N+1];
		indegree = new int[N+1];
		
		Arrays.fill(subject, 1);
		topology();
		
		for(int i=1; i<=N; i++) {
			System.out.print(subject[i]+" ");
		}
	}
	
	public static void topology() {
		for(int i=1; i<=N; i++) {
			for(Node next : graph.get(i)) {
				indegree[next.idx]++; // 앞선 과목이 있는 과목들은 차수 증가
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(Node next : graph.get(cur)) {
				// 선수과목을 들은 후 현재 과목을 들을 수 있음
	            subject[next.idx] = Math.max(subject[next.idx], subject[cur] + 1);
	            
	            indegree[next.idx]--;
	            
	            if(indegree[next.idx]==0) {
	            	q.offer(next.idx);
	            }
			}
		}
	}
}
