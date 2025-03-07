import java.io.IOException;
import java.io.*;
import java.util.*;

// 일부 학생들의 키를 비교한 결과를 바탕으로 줄을 세우는 프로그램 작성
// N - 학생 수, M - 키를 비교한 횟수
// A가 학생 B의 앞에 서야함.
// 같아도 앞에 서야하나..? 

public class Main {
	static int N, M;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] indegree;
	static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		indegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			graph.get(n1).add(n2);
		}

		// 차수 증가
		for (int i = 1; i <= N; i++) {
			for (int cur : graph.get(i)) {
				indegree[cur]++;
			}
		}

		topology();
		
		for(int i=0; i<N; i++) {
			System.out.print(result.get(i)+" ");
		}
	}

	public static void topology() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				q.offer(i);
				result.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph.get(cur)) {
				indegree[next]--;
				if(indegree[next]==0) {
					q.offer(next);
					result.add(next);
				}
			}
		}
	}
}
