import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, sy, sx, ey, ex;
    static List<int[]> stores;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++){
            N = Integer.parseInt(br.readLine());
            stores = new ArrayList<>();
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            sy = Integer.parseInt(st.nextToken());
            sx = Integer.parseInt(st.nextToken());

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                stores.add(new int[]{y, x});
            }

            st = new StringTokenizer(br.readLine());
            ey = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());

            simulate(sy, sx);
        }
    }

    public static void simulate(int sy, int sx){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sy, sx});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            // 현재 위치에서 락페스티벌 갈 수 있는지 확인
            if(validCheck(cur[0], cur[1], ey, ex)) {
                System.out.println("happy");
                return;
            }

            // 편의점을 갈 수 있는지 확인
            for(int i=0; i<stores.size(); i++){
                if(!visited[i]){
                    int y = stores.get(i)[0];
                    int x = stores.get(i)[1];
                    if(validCheck(cur[0], cur[1], y, x)){
                        visited[i] = true;
                        q.offer(new int[]{y, x});
                    }
                }

            }

        }

        System.out.println("sad");
    }

    public static boolean validCheck(int sy, int sx, int ey, int ex){
        if(Math.abs(ey-sy)+Math.abs(ex-sx)<=1000) return true;
        return false;
    }
}
