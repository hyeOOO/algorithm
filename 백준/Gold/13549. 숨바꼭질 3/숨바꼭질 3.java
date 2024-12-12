import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 방문을 했는데 해당 방문보다 더 작게 방문이 가능한 경우가 있으므로 방문배열 선언 필요x
public class Main {
	static int K;
	static int[] time = new int[100001];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(time, Integer.MAX_VALUE);
		
		if(N==K) System.out.println(0);
		else bfs(N);
	}
	
	static void bfs(int start) {
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offerFirst(start);
		time[start] = 0;
		
		while(!dq.isEmpty()) {
			int cur = dq.pollFirst();
			
			if(cur==K) {
				System.out.println(time[cur]);
				return;
			}
			
			// 순간이동이 시간소요가 없으니 먼저 처리
			if(cur*2<100001 && time[cur*2]>time[cur]) {
				dq.offerFirst(cur*2);
				time[cur*2] = time[cur];
 			}
			
			// 전진
			if(cur+1<100001 && time[cur+1]>time[cur]+1) {
				dq.offerLast(cur+1);
				time[cur+1] = time[cur]+1;
			}
			
			// 후진
			if(cur-1>=0 && time[cur-1]>time[cur]+1) {
				dq.offerLast(cur-1);
				time[cur-1]=time[cur]+1;
			}
		}
		
		System.out.println(-1);
		return;
	}
}
