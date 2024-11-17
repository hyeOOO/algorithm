import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] target = new String[N];
		
		for(int i=0; i<N; i++) {
			target[i] = br.readLine();
		}
		
		int count = 0;
		for(int j=0; j<M; j++) {
			String str = br.readLine();
			
			for(int i=0; i<N; i++) {
				if(target[i].equals(str)) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}
}
