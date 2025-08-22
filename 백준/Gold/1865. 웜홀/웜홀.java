import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, W;
    static List<Edge> edges;
    static class Edge{
        int from;
        int to;
        int cost;

        Edge(int f, int t, int c){
            this.from = f;
            this.to = t;
            this.cost = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=1; t<=tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, cost));
                edges.add(new Edge(to, from, cost));
            }

            for(int i=0; i<W; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, -cost));
            }

            if(hasNegativeCycle()){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static boolean hasNegativeCycle(){
        int[] dist = new int[N+2]; // N+1개 + 가상노드 1개
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N+1] = 0; // 가상 노드

        // 가상 노드에서 모든 노드로 가는 간선 추가 (가중치 0)
        for(int i = 1; i <= N; i++){
            edges.add(new Edge(N+1, i, 0));
        }

        // 벨만포드 실행
        for(int i=0; i<N; i++){ // N번 반복 (가상노드 포함)
            for(Edge edge : edges){
                if(dist[edge.from] != Integer.MAX_VALUE &&
                        dist[edge.from] + edge.cost < dist[edge.to]){
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        // 음수 사이클 체크
        for(Edge edge : edges){
            if(dist[edge.from] != Integer.MAX_VALUE &&
                    dist[edge.from] + edge.cost < dist[edge.to]){
                return true;
            }
        }
        return false;
    }
}
