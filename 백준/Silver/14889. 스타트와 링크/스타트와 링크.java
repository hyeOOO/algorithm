import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[] visited;
	static int N; 
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		
		System.out.println(min);
	}
	
	public static void comb(int idx, int depth) {
		if(depth==N/2) {
			diff();
			return;
		}
		
		for(int i= idx; i<N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				comb(i+1, depth+1);
				visited[i]=false;
			}
		}
	}
	
	public static void diff() {
		int startTeam = 0;
		int linkTeam = 0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(visited[i]&&visited[j]) {
					startTeam += arr[i][j];
					startTeam += arr[j][i];
				}else if(!visited[i]&&!visited[j]) {
					linkTeam += arr[i][j];
					linkTeam += arr[j][i];
				}
			}
		}
		
		int diff = Math.abs(startTeam-linkTeam);
		
		if(diff==0) {
			System.out.println(0);
			System.exit(0); // 바로 종료
		}
		
		min = Math.min(min, diff);
	}
}
