import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];

        for(int i=0; i<N; i++){
            parent[i] = i;
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // 같은 집합에 속해있다면 사이클 형성
            if(find(n1) == find(n2)) {
                System.out.println(i);
                return;
            }

            // 다른 집합이면 합치기
            union(n1, n2);
        }

        System.out.println(0);
    }

    public static int find(int n){
        if(parent[n]!=n){
            parent[n] = find(parent[n]);
        }

        return parent[n];
    }

    public static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
