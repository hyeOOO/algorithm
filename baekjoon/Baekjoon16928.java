package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16928 {
	static int[] game = new int[101];
	static int[] visit = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for(int i=1; i<game.length; i++) {
			game[i] = i;
		}
		
		while(N-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			game[a] = b;
		}
		while(M-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			game[a] = b;
		}

		int result = bfs(1);
		
		System.out.println(result);
	}

	public static int bfs(int current) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(current);
		visit[current] = 0;
		
		while (true) {
			current = q.poll();
			for (int i = 1; i <= 6; i++) {
				int next = current + i;
				
				if(next > 100) continue;
				
				if(visit[game[next]]==0) {
					q.offer(game[next]);
					visit[game[next]]=visit[current]+1;
				}
				if(game[next]==100) {
					return visit[100];
				}
			}
		}
	}
}
