import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] ingredients = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());		
		}
		
		int minDiff = Integer.MAX_VALUE;
		
		for(int mask = 1; mask<(1<<N); mask++) {
			int totalS = 1;
			int totalD = 0;
			
			for(int i=0; i<N; i++) {
				if ((mask & (1 << i)) != 0) { // i번째 비트가 1인지 확인
					totalS *= ingredients[i][0]; // 신맛은 곱하고
					totalD += ingredients[i][1]; // 쓴맛은 더하기
                }
			}
			
			minDiff = Math.min(minDiff, Math.abs(totalS-totalD));
		}
		
		System.out.println(minDiff);
	}
}
