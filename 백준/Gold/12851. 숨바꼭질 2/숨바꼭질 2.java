import java.io.*;
import java.util.*;

public class Main {
    static int N, K, result = Integer.MAX_VALUE, count = 0;
    
    static class Node {
        int pos;
        int step;
        
        public Node(int pos, int step) {
            this.pos = pos;
            this.step = step;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else {
            bfs();
            System.out.println(result);
            System.out.println(count);
        }
    }
    
    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        int[] visited = new int[100001]; // 방문 배열을 int로 선언 
        
        Arrays.fill(visited, -1); // -1로 방문하지 않았음을 처리
        
        q.offer(new Node(N, 0));
        visited[N] = 0;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            // 이미 시간이 result를 초과하면 볼 필요 없음
            if (cur.step > result) continue;
            
            // 만약에 동생을 찾게되었을때
            if (cur.pos == K) {
                if (result > cur.step) { // 현재 방식이 더 적게 시간이 든다면 초기화
                    result = cur.step;
                    count = 1;
                } else if (result == cur.step) { //같다면 ++
                    count++;
                }
                continue;
            }
            
            // 다음 위치 구하기
            int[] nextPos = {cur.pos * 2, cur.pos + 1, cur.pos - 1};
            for (int next : nextPos) {
                if (next >= 0 && next <= 100000) {
                    // 방문하지 않았거나 다른 경로로 같은 최단 거리에 도달하는 경우를 처리
                    if (visited[next] == -1 || visited[next] == cur.step + 1) {
                        visited[next] = cur.step + 1;
                        q.offer(new Node(next, cur.step + 1));
                    }
                }
            }
        }
    }
}