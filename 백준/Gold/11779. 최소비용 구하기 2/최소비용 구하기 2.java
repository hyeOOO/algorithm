import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, start, end;
    static List<List<Node>> graph;
    static int[] dist, prev;

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(n1).add(new Node(n2, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        
        System.out.println(dist[end]);

        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != 0) {
            path.add(current);
            current = prev[current];
        }

        System.out.println(path.size());
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    static void dijkstra() {
        dist = new int[N + 1];
        prev = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > dist[curNode.node]) continue;

            for (Node next : graph.get(curNode.node)) {
                int newCost = curNode.cost + next.cost;
                if (newCost < dist[next.node]) {
                    dist[next.node] = newCost;
                    prev[next.node] = curNode.node;
                    pq.offer(new Node(next.node, newCost));
                }
            }
        }

    }
}
