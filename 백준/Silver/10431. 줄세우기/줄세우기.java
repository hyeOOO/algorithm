import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
        int N = Integer.parseInt(br.readLine());
		
        for (int i=1; i<N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			
            Integer.parseInt(st.nextToken());
 
            int cnt = 0;
            int[] num = new int[20];
			
            for (int j=0; j<20; j++) {
                num[j] = Integer.parseInt(st.nextToken());
            }
			
            for (int j=0; j<20; j++) {
                for (int k=0; k<j; k++) {
                    if (num[k] > num[j]) cnt++;
                }
            }
            
            System.out.println(i + " " + cnt);
        }
	}
	
	
}
