import java.util.*;

class Solution {
    private ArrayList<Integer>[] list;
    private boolean[] visited;
    private int answer = 0;
    private int max = 0;
    
    public void bfs(int v){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {v, 0});
        visited[v] = true;
        
        while(!q.isEmpty()){
            int val = q.peek()[0];
            int depth = q.peek()[1];
            q.poll();
            
            if(max == depth) answer++;
            else if(max<depth){
                max = depth;
                answer = 1;
            }
            
            for(int i=0; i<list[val].size(); i++){
                int next = list[val].get(i);
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(new int[]{next, depth+1});
                }
            }
        }
        
    }
    
    public int solution(int n, int[][] edge) {
        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<edge.length; i++){
            int v1 = edge[i][0];
            int v2 = edge[i][1];
            
            list[v1].add(v2);
            list[v2].add(v1);
        }
        
        bfs(1);
        
        return answer;
    }
}