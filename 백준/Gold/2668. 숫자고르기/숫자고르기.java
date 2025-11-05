import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Set<Integer> answer = new HashSet<>();
    static int[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++){
            visited = new boolean[N+1];
            dfs(i, i);
        }

        // 정렬 후 출력
        List<Integer> result = new ArrayList<>(answer);
        Collections.sort(result);
        System.out.println(result.size());
        for(int n : result){
            System.out.println(n);
        }
    }

    public static void dfs(int start, int cur){
        visited[cur] = true;
        int next = graph[cur];

        if(next==start){
            int node = start;
            while(true){
                answer.add(node);
                node = graph[node];
                if(node==start) break;
            }
        }else if(!visited[next]){
            dfs(start, next);
        }
    }
}
