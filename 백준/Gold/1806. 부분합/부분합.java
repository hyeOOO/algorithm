import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = 0;
		long total = 0;
		int min = Integer.MAX_VALUE;
		
		while(left<=right && left<N) {
			if(total>=S) {
				min = Math.min(min, right-left);
				total -= arr[left++];
			}else if(total<S && right<N) {
				total += arr[right++];
			}else {
				break;
			}
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(0);
			return;
		}
		System.out.println(min);
		
	}
}
