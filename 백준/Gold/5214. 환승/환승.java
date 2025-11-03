import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, M;
    static int[] dist;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dist = new int[N+M+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0; i<=N+M; i++){ // 하이퍼튜브도 포함해서 생성
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            int hypertubeIdx = N+i+1; // 하이퍼튜브 번호
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<K; j++){
                int station = Integer.parseInt(st.nextToken());
                graph.get(hypertubeIdx).add(station);
                graph.get(station).add(hypertubeIdx);
            }
        }

        bfs(1, N);

        System.out.println(dist[N]==Integer.MAX_VALUE?-1:dist[N]);
    }

    public static void bfs(int s, int e){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{s, 1});
        dist[s] = 1;
        boolean[] visited = new boolean[N+M+1];
        visited[s] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == e) return; // 도착하면 종료

            for(int next : graph.get(cur[0])){
                if(!visited[next]){
                    visited[next] = true;
                    if(cur[0]>N){ // 현재 하이퍼튜브를 타고 다음으로 이동할 때
                        dist[next] = Math.min(dist[next], cur[1]);
                    }else{
                        dist[next] = Math.min(dist[next], cur[1]+1);
                    }
                    q.offer(new int[]{next, dist[next]});
                }
            }
        }
    }
}
