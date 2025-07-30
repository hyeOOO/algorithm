import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static List<Node> graph = new ArrayList<>();
    static int[] parents;
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        Node(int f, int t, int c){
            this.from = f;
            this.to = t;
            this.cost = c;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V+1];

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(f,t,c));
        }

        Collections.sort(graph);

        init();
        int totalCost = 0;
        int visitedCount = 0;

        for(Node node : graph){
            if(union(node.from, node.to)){
                totalCost += node.cost;
                visitedCount++;

                if(visitedCount==V-1) break;
            }
        }

        System.out.println(totalCost);

    }

    public static boolean union(int from ,int to){
        int fromRoot = findSet(from);
        int toRoot = findSet(to);

        if(fromRoot == toRoot) return false;
        parents[toRoot] = fromRoot;
        return true;
    }

    public static int findSet(int n){
        if(parents[n]==n) return n;
        return parents[n] = findSet(parents[n]);
    }

    public static void init(){
        for(int i=1; i<=V; i++){
            parents[i] = i;
        }
    }
}
