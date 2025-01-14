import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int result = 0;
	static class Node {
		private int node;
		private int weight;
		
		public Node(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		public int getNode() {
			return node;
		}

		public void setNode(int node) {
			this.node = node;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Node>[] tree = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=1; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			tree[parent].add(new Node(child, weight));
			tree[child].add(new Node(parent, weight));
		}
		
		for(int i=1; i<=n; i++) {
			boolean[] visited = new boolean[n+1];
			visited[i] = true;
			dfs(tree, visited, 0, i);
		}
		
		System.out.println(result);
	}
	
	private static void dfs(List<Node>[] tree, boolean[] visited, int sum, int start) {
		for(int i=0; i<tree[start].size(); i++) {
			Node node = tree[start].get(i);
			
			if(!visited[node.getNode()]) {
				visited[node.getNode()] = true;
				dfs(tree, visited, sum+node.getWeight(), node.getNode());
				visited[node.getNode()] = false;
			}
		}
		
		result = Math.max(result, sum);
	}
}
