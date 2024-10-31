import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min;
	static int[][] map;
	static List<int[]> chickens;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// N과 M 입력받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chickens = new ArrayList<int[]>();
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					// 치킨집을 list에 저장
					chickens.add(new int[] { i, j });
				}
			}
		}
		
		// q
		visited = new boolean[chickens.size()];
		min = Integer.MAX_VALUE;
		
		comb(0, 0);
		
		System.out.println(min);
	}
	
	// 조합 구하기 (현재 치킨집의 갯수)C m 개 선택
	public static void comb(int index, int depth) {
		if(depth==M) {
			min = Math.min(min, getDistance());
			return;
		}
		
		for(int i=index; i<chickens.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i, depth+1);
				visited[i] = false;
			}
		}
		
	}
	
	// 뽑힌 애들 중에서도 가까운 거리를 구해야하군ㅜ
	public static int getDistance() { // 일반 집에서 치킨집까지의 거리 구하기
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) { // 일반 집에서 출발해서
					int minDist = Integer.MAX_VALUE;
					for(int k=0; k<chickens.size(); k++) {
						if(visited[k]) { // 선정된 치킨집까지의 거리를 구하는데
							int[] coord = chickens.get(k);
							
							int dist = Math.abs(coord[0]-i)+Math.abs(coord[1]-j);
							minDist = Math.min(minDist, dist); // 제일 최소거리를 구해야함
						}
					}
					
					result+=minDist;
				}
			}
		}
		
		return result;
	}
}
