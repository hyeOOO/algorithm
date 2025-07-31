import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;
    static List<Node> graph = new ArrayList<>();
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];

        init();
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Node(from, to, cost));
        }

        Collections.sort(graph);

        int totalCost = 0;
        int lastCost = 0;
        int nodeCount = 0;
        for(Node node : graph){
            if(union(node.from, node.to)){
                totalCost += node.cost;
                nodeCount++;
                lastCost = node.cost;
                if(nodeCount==N-1) break;
            }
        }

        System.out.println(totalCost-lastCost);

    }

    public static boolean union(int from, int to){
        int fromRoot = findSet(from);
        int toRoot = findSet(to);

        if(fromRoot==toRoot) return false;

        parents[toRoot] = fromRoot;
        return true;
    }

    public static int findSet(int n){
        if(parents[n] == n) return parents[n];
        return parents[n] = findSet(parents[n]);
    }

    public static void init(){
        for(int i=1; i<=N; i++){
            parents[i] = i;
        }
    }
}
