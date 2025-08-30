import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, Q, X, D, K, result;
	static int[][] darts;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	static List<int[]> deleteList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		darts = new int[N+1][M];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				darts[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(Q --> 0) {
			st = new StringTokenizer(br.readLine());
			
			X = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken()); // 0: 시계, 1: 반시계
			K = Integer.parseInt(st.nextToken());
			
			for(int i=X; i<=N; i+=X) {
				int[] target = darts[i];
				rotateDart(target);
			}
			
			DeleteAdjacentNumber();
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				result += darts[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	public static void DeleteAdjacentNumber() {
		deleteList = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(darts[i][j]==0) continue;
				check(i, j);
			}
		}
		
		if(deleteList.isEmpty()) {
			// 정규화 필요
			normalization();
		}else {
			for(int[] list : deleteList) {
				int y = list[0];
				int x = list[1];
				darts[y][x] = 0;
			}
		}
	}
	
	public static void normalization() {
		int total = 0;
		int count = 0;
		double avg = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(darts[i][j]!=0) {
					total += darts[i][j];
					count++;
				}
			}
		}
		
		avg = (double)total/count;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(darts[i][j]==0) continue;
				if(darts[i][j]>avg) {
					darts[i][j]-=1;
				}else if(darts[i][j]<avg) {
					darts[i][j]+=1;
				}
			}
		}
	}
	
	public static void check(int y, int x) {
		if(x==0&&darts[y][M-1]==darts[y][x]) {
			deleteList.add(new int[] {y, x});
			return;
		}
		
		if(x==M-1&&darts[y][0]==darts[y][x]) {
			deleteList.add(new int[] {y, x});
			return;
		}
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny<=0||nx<0||ny>N||nx>=M) continue;
			
			if(darts[ny][nx]==0) continue;
			if(darts[y][x]==darts[ny][nx]) {
				deleteList.add(new int[] {y, x});
				return;
			}
		}
	}
	
	public static void rotateDart(int[] target) {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int i=0; i<M; i++) {
			dq.add(target[i]);
		}
		
		if(D==0) { // 시계 방향
			for(int i=0; i<K; i++) {
				int last = dq.pollLast();
				dq.addFirst(last);
			}
			
		}else { // 반시계 방향
			for(int i=0; i<K; i++) {
				int first = dq.pollFirst();
				dq.addLast(first);
			}
		}
		
		int size = dq.size();
		for(int i=0; i<size; i++) {
			target[i] = dq.poll();
		}
	}
}
