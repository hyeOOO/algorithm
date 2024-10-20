import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] a;
	static boolean[] visit;
	static int n, m, v;
	static int count = 0;

	public static int bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visit[i] = true;

		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int k = 0; k < a[temp].size(); k++) {
				int next = a[temp].get(k);
				if (!visit[next]) {
					q.offer(next);
					visit[next] = true;
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		v = 1;
		a = new ArrayList[n + 1];
		visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			a[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			a[u].add(v);
			a[v].add(u);
		}

		System.out.println(bfs(1));
	}
}
