import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long C;
	public static void main(String[] args) throws IOException{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(pow(A,B));
		
		
	}
	
	public static long pow(long a, long exp) {
		if(exp==1) {
			return a%C;
		}
		
		long temp = pow(a, exp/2);
		
		if(exp%2==1) {
			return (temp*temp%C)*a%C;
		}
		return temp * temp % C;
	}
}
