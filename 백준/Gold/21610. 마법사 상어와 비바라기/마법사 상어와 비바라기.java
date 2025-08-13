import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dy = {-1, -1, 1, 1};
	static int[] dx = {-1, 1, 1, -1};
	static Queue<int[]> targets = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			moveCloud(d, s%N, i); // 구름 이동
			addWater(); // 비 내리기
			copyWater(); // 물 복사 마법
			addCloud(); // 다음 구름 찾기			
		}
		
		int result = 0;
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				result+=map[y][x];
			}
		}
		
		System.out.println(result);
	}
	
	public static void addCloud() {
		Queue<int[]> nextCloud = new LinkedList<>();
		boolean[][] isCloud = new boolean[N][N];
		
		while(!targets.isEmpty()) {
			int[] cur = targets.poll();
			isCloud[cur[0]][cur[1]] = true;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isCloud[i][j]) continue;
				
				if(map[i][j]>=2) {
					nextCloud.add(new int[] {i, j});
					map[i][j]-=2;
				}
			}
		}
		
		targets.clear();
		targets.addAll(nextCloud);
	}
	
	public static void copyWater() {
		Queue<int[]> temp = new LinkedList<int[]>();
		
		while(!targets.isEmpty()) {
			int[] cur = targets.poll();
			int cy = cur[0];
			int cx = cur[1];
			int count = 0;
			
			for(int d=0; d<4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(ny<0||nx<0||ny>=N||nx>=N) continue;
				
				if(map[ny][nx]>0) count++;
			}
			
			map[cy][cx] += count;
			temp.add(cur);
		}
		
		targets.addAll(temp);
	}
	
	public static void addWater() {
		Queue<int[]> temp = new LinkedList<int[]>();
		
		while(!targets.isEmpty()) {
			int[] cur = targets.poll();
			
			map[cur[0]][cur[1]]++;
			
			temp.add(cur);
		}
		
		targets.addAll(temp);
	}
	
	public static void moveCloud(int d, int s, int count) {
		Queue<int[]> temp = new LinkedList<int[]>();
		
		if(count==0) { // 처음에 구름이 생기는 위치는 (N-1, 1), (N-1, 2), (N,1), (N,2)임.
			int endY = N-1;
			targets.add(new int[] {endY-1, 0});
			targets.add(new int[] {endY-1, 1});
			targets.add(new int[] {endY, 0});
			targets.add(new int[] {endY, 1});
		}
		
		while(!targets.isEmpty()) {
			int[] cur = targets.poll();
			int ny = cur[0];
			int nx = cur[1];
			switch(d) {
			case 1:
				nx = ((cur[1]-s)+N)%N;
				break;
			case 2:
				ny = ((cur[0]-s)+N)%N;
				nx = ((cur[1]-s)+N)%N;
				break;
			case 3:
				ny = ((cur[0]-s)+N)%N;
				break;
			case 4:
				ny = ((cur[0]-s)+N)%N;
				nx = (cur[1]+s)%N;
				break;
			case 5:
				nx = (cur[1]+s)%N;
				break;
			case 6:
				ny = (cur[0]+s)%N;
				nx = (cur[1]+s)%N;
				break;
			case 7:
				ny = (cur[0]+s)%N;
				break;
			case 8:
				ny = (cur[0]+s)%N;
				nx = ((cur[1]-s)+N)%N;
				break;
			}
			
			temp.add(new int[] {ny, nx});
		}
		
		targets.addAll(temp);
		temp.clear();
	}
}
