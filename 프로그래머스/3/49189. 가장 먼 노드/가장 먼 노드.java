import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    static int[] dist;
    public int solution(int n, int[][] edge) {
        graph = new ArrayList<>();
        dist = new int[n+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<edge.length; i++){
            int to = edge[i][0];
            int from = edge[i][1];
            
            graph.get(to).add(from);
            graph.get(from).add(to);
        }
        
        bfs(n);
        
        int answer = getAnswer(n);
        
        return answer;
    }
    
    public static int getAnswer(int n){
        Arrays.sort(dist);
        
        int max = dist[n-1];
        int answer = 0;
        
        for(int i=n-1; i>=0; i--){
            if(max==dist[i]) answer++;
        }
        
        return answer;
    }
    
    public static void bfs(int n){
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        dist[1] = 0;
        q.offer(new int[]{1, 0});
        visited[1] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int d = cur[1];
            
            for(int next : graph.get(node)){
                if(!visited[next]){
                    dist[next] = Math.min(dist[next], d+1);
                    visited[next] = true;
                    q.offer(new int[]{next, d+1});
                }
            }
        }
    }
}