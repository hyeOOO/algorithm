import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] items;
    static List<List<Node>> graph = new ArrayList<>();
    
    static class Node implements Comparable<Node> {
        int next, cost;
        
        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        items = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }
        
        int maxItems = 0;
        for(int i=1; i<=N; i++) {
            maxItems = Math.max(maxItems, dijkstra(i));
        }
        
        System.out.println(maxItems);
    }
    
    static int dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(cur.cost > dist[cur.next]) continue;
            
            for(Node next : graph.get(cur.next)) {
                int newCost = cur.cost + next.cost;
                if(newCost < dist[next.next] && newCost <= M) {
                    dist[next.next] = newCost;
                    pq.offer(new Node(next.next, newCost));
                }
            }
        }
        
        int totalItems = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] <= M) totalItems += items[i];
        }
        
        return totalItems;
    }
}
