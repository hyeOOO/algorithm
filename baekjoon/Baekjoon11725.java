package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon11725 {
	public static int N;
	public static boolean[] visit;
	public static ArrayList<Integer>[] list;
	public static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		list = new ArrayList[N + 1];
		parent = new int[N+1];

		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(1);
		
		for(int i=2; i<N+1; i++) {
			System.out.println(parent[i]);
		}
		
	}
	
	public static void dfs(int n) {
		visit[n] = true;
		for(int a : list[n]) {
			if(visit[a]==false) {
				parent[a] = n;
				dfs(a);
			}
		}
	}
}
