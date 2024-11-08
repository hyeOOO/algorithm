import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, blue, white;
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr= new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}
	
	public static void dfs(int y, int x, int size) {
		if(valid(y, x, size)) {
			if(arr[y][x]==1) blue++;
			else white++;
		}else {
			int resize = size/2;
			dfs(y, x, resize);
			dfs(y+resize, x, resize);
			dfs(y, x+resize, resize);
			dfs(y+resize, x+resize, resize);
		}
	}
	
	public static boolean valid(int y, int x, int size) {
		int start = arr[y][x];
		
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				if(start!=arr[i][j]) return false;
			}
		}
		
		return true;
	}
}
