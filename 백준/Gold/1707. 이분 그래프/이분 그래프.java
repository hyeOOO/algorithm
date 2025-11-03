import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;
    static List<List<Integer>> graph;
    static int[] color; // 각 정점의 집합을 나누기 위한 색 정보(1-레드, 2-블루)
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            color = new int[V+1];
            visited = new boolean[V+1];

            for(int i=0; i<=V; i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            boolean answer = true;
            for(int i=1; i<=V; i++){
                if(!visited[i]){
                    if(!bfs(i)){
                        answer = false;
                        break;
                    }
                }
            }

            System.out.println(answer?"YES":"NO");
        }

    }

    public static boolean bfs(int s){
        Queue<int[]> q = new LinkedList<>();
        color[s] = 1;
        q.offer(new int[]{s,color[s]});
        visited[s] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int next : graph.get(cur[0])){
                if(color[next]==cur[1]) return false;
                if(!visited[next]){
                    visited[next] = true;
                    int nextColor = 0;
                    if(cur[1]==1){
                        nextColor = 2;
                    }else nextColor = 1;
                    color[next] = nextColor;
                    q.offer(new int[]{next, nextColor});
                }
            }
        }

        return true;
    }
}
