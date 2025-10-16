// 다익스트라?
import java.util.*;

class Solution {
    static List<List<Node>> graph = new ArrayList<>();
    static int[] dist;
    
    static class Node implements Comparable<Node>{
        int to;
        int cost;
        
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        dist = new int[N+1];
        int answer = 0;
        
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int cost = road[i][2];
            
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }
        
        dijkstra(1);
        
        for(int i=1; i<=N; i++){
            if(dist[i]<=K) answer++;
        }
        
        return answer;
    }
    
    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[dist.length];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(start,0));
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(visited[cur.to]) continue;  // 이미 처리한 노드는 스킵
            visited[cur.to] = true;
            
            for(Node next : graph.get(cur.to)){
                if(dist[next.to] > dist[cur.to]+next.cost){
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}