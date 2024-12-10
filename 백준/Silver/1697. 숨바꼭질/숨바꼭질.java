import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static boolean[] visited = new boolean[100001];
	static int[] time = new int[100001];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N==K) System.out.println(0);
		else bfs(N);
	}
	
	public static void bfs(int N) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur==K) {
				System.out.println(time[cur]);
				return;
			}
			int next = 0;
			// 세가지 경우의 수(x-1, x+1, 2*x)를 다음 스텝으로 구하기
			for(int i=0; i<3; i++) {
				if(i==0) next = cur-1;
				else if(i==1) next = cur+1;
				else next = cur*2;
				
				// 다음 이동이 범위에서 벗어나거나 방문한 위치면 방문x
				if(next>=0 && next<=100000 && !visited[next]) {
					q.offer(next);
					visited[next] = true;
					time[next] = time[cur]+1;
				}
			}
		}
		
		System.out.println(-1);
		return;
	}
}
