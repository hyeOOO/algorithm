import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int result;
	static int[] parent;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N + 1]; // 각 노드의 부모노드 정보
			rank = new int[N + 1]; // 트리 높이
			result = 0;

			for (int i = 1; i <= N; i++) {
				parent[i] = i; // 부모노드 초기화
				rank[i] = 0; // 트리 높이 초기화
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				union(from, to);
			}

			for (int i = 1; i <= N; i++) {
				if (find(i) == i)
					result++;
			}

			System.out.printf("#%d %d\n", t, result);
		}
	}

	public static int find(int n) {
		if (parent[n] != n) {
			parent[n] = find(parent[n]);
		}

		return parent[n];
	}

	public static void union(int n1, int n2) {
		int rootN1 = find(n1);
		int rootN2 = find(n2);

		if (rootN1 != rootN2) {
			if (rank[rootN1] > rank[rootN2]) {
				parent[rootN2] = rootN1;
			} else if (rank[rootN1] < rank[rootN2]) {
				parent[rootN1] = rootN2;
			} else {
				parent[rootN2] = rootN1;
				rank[rootN1]++;
			}
		}
	}
}
