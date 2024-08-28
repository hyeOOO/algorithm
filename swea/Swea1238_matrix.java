package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea1238_matrix {
	static int N;
	static int start;
	static int size = 101;
	static boolean[] visited;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix = new int[size][size];
			visited = new boolean[size];

			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				matrix[from][to] = 1;
			}

			int result = bfs(start);
			
			System.out.printf("#%d %d\n",t, result);
		}
	}

	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		ArrayList<Integer> list = new ArrayList<>();
		int max = 0;

		while (!q.isEmpty()) {
			max = 0;
			int qSize = q.size();

			for (int s = 0; s < qSize; s++) {
				int cur = q.poll();
				for (int i = 1; i < size; i++) {
					if (matrix[cur][i] == 1 && !visited[i]) {
						q.offer(i);
						visited[i] = true;
						max = Math.max(max, i);
					}
				}
			}
			
			list.add(max);
		}
				
		return list.get(list.size()-2);
	}
}
