import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, X;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visited = new boolean[100001]; // 최대 위치는 100,000으로 설정

        bfs();
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { N, 0 });
        visited[N] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == X) {
                System.out.println(cur[1]);
                return;
            }

            // 뒤로 한 칸 이동
            if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
                visited[cur[0] - 1] = true;
                q.offer(new int[] { cur[0] - 1, cur[1] + 1 });
            }

            // 앞으로 한 칸 이동
            if (cur[0] + 1 <= 100000 && !visited[cur[0] + 1]) {
                visited[cur[0] + 1] = true;
                q.offer(new int[] { cur[0] + 1, cur[1] + 1 });
            }

            // 순간이동 (2배)
            if (cur[0] * 2 <= 100000 && !visited[cur[0] * 2]) {
                visited[cur[0] * 2] = true;
                q.offer(new int[] { cur[0] * 2, cur[1] + 1 });
            }
        }
    }
}