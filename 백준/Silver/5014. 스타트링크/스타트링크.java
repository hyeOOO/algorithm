import java.io.*;
import java.util.*;

public class Main {
	static int F,S,G,U,D;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 총 f층이고, 스타트링크는 g층에 있고 강호는 s층에 있다.
		// 엘베는 버튼 2개밖에 없다(u,d) u는 위로 u층 가고 d는 아래로 d층 감(u층 위나 d층 아래가 없을 경우 엘베 안움직임)
		// g층가려면 버튼을 최소 몇번 눌러야하나?(g층 못가면 use the stairs)
		
		// f,s,g,u,d 순
		// 10 1 10 2 1이면, 총 10층 건물에 강호는 1층에 있고 10층을 가야함
		// 2층 업과 1층 다운이 있음.
		// 1->3->5->7->9->8->10(6번)
		// 100 2 1 1 0, 100층 건물에 강호는 2층에 목표는 1층에 
		// 못감
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// dfs같은데 못갈수도 있으니 BFS같음
		// 스타트를 기준으로 큐에 넣고 +U과 -D만큼을 큐에 추가로 넣고 G와 같을때까지 반복.
		// 큐가 비면, 못도달하는거니까 use the stairs 출력.
		// 방문처리 해야할 것 같음.
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F+1];
		
		bfs();
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {S,0});
		visited[S] = true;
		
		while(!q.isEmpty()) {
			int current = q.peek()[0];
			int count = q.peek()[1];
			q.poll();
			
			if(current==G) {
				System.out.println(count);
				return;
			}
			int up = current+U;
			int down = current-D;
			
			if(up<=F&&!visited[up]) {
				q.offer(new int[] {up,count+1});
				visited[up]=true;
			}
			
			if(down>=1&&!visited[down]) {
				q.offer(new int[] {down,count+1});
				visited[down]=true;
			}
		}
		
		System.out.println("use the stairs");
		return;
	}
}
