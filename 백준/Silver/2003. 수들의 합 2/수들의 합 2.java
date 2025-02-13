import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N= Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		
		for(int i=0; i<N; i++) {
			if(arr[i]==M) {
				count++;
				continue;
			}
			int total = arr[i];
			for(int j=i+1; j<N; j++) {
				total+=arr[j];
				if(total == M) {
					count++;
					continue;
				}
			}
		}
		
		System.out.println(count);
	}
}
