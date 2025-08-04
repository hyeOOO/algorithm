import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, goal;
    static int[] indegree;
    static int[] completionTime;
    static int[] buildTime;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc=0; tc<t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            indegree = new int[N+1];
            completionTime = new int[N+1];
            buildTime = new int[N+1];
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++){
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                graph.get(n1).add(n2);
                indegree[n2]++;
            }

            goal = Integer.parseInt(br.readLine());

            topologicalSort();

            System.out.println(completionTime[goal]);
        }
    }


    public static void topologicalSort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++){ // 부모노드가 없는 노드부터 출발
            if(indegree[i]==0){
                q.offer(i);
                completionTime[i] = buildTime[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph.get(cur)){
                indegree[next]--; // 진입 차수 감소

                // 완공시간 업데이트
                completionTime[next] = Math.max(completionTime[next],
                        completionTime[cur]+buildTime[next]);

                if(indegree[next]==0){
                    q.offer(next);
                }
            }
        }
    }
}
