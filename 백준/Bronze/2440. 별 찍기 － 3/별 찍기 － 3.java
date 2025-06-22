import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int y=0; y<n; y++) {
			for(int x=n-y; x>0; x--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
