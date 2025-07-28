import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] subTreeCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        subTreeCount = new int[N+1];

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        makeTree(R, -1);
        getSubTreeCount(R);

        for(int i=0; i<Q; i++){
            int p = Integer.parseInt(br.readLine());
            System.out.println(subTreeCount[p]);
        }
    }

    public static void makeTree(int cur, int parent){
        for(int next : graph.get(cur)){
            if(next!=parent){
                tree.get(cur).add(next);
                makeTree(next, cur);
            }
        }
    }

    public static int getSubTreeCount(int p){
        subTreeCount[p] = 1;
        for(int child : tree.get(p)){
            subTreeCount[p] += getSubTreeCount(child);
        }

        return subTreeCount[p];
    }
}
