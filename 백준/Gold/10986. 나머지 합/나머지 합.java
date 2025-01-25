import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		long[] prefixModCount = new long[M]; // 나머지 갯수 저장(M=3일때, 나머지가 0, 1, 2 인 경우들 다 세기)
		long sum = 0; //누적합
		long result = 0;
		
		prefixModCount[0]++; // 누적합이 0일때 나머지 0이므로 카운트 증가
		
		for(int i=0; i<N; i++) {
			sum+=num[i];
			int mod = (int)(sum%M);
			
			result += prefixModCount[mod];
			prefixModCount[mod]++;
		}
		
		System.out.println(result);
	}
}
