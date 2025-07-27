import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if(n1==-1 && n2==-1) break;

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        List<Integer> presidents = new ArrayList<>();
        int minScore = Integer.MAX_VALUE;

        for(int i=1; i<=N; i++){
            boolean[] visited = new boolean[N+1];
            int[] dist = new int[N+1];
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;
            dist[i] = 0;

            while(!q.isEmpty()){
                int cur = q.poll();

                for(int next : graph.get(cur)){
                    if(!visited[next]){
                        visited[next] = true;
                        dist[next] = dist[cur]+1;
                        q.offer(next);
                    }
                }
            }

            Arrays.sort(dist);
            if(dist[N]<minScore){
                minScore = dist[N];
                presidents.clear();
                presidents.add(i);
            }else if(dist[N]==minScore){
                presidents.add(i);
            }
        }

        System.out.println(minScore+" "+presidents.size());
        for(int president : presidents){
            System.out.print(president+" ");
        }
    }
}
