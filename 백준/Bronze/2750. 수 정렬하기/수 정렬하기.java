import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Integer> arr = new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(!arr.contains(n)) arr.add(n);
		}
		
		Collections.sort(arr);
		
		for(int n : arr) {
			System.out.println(n);
		}
	}
}
