import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
		
		int result = N; 
		
		for(int i=0; i<N; i++) {
			Set<Character> s = new HashSet<>();
			
			s.add(arr[i].charAt(0));
			
			for(int j=1; j<arr[i].length(); j++) {
				char preChar = arr[i].charAt(j-1);
				char curChar = arr[i].charAt(j);
				
				if(!s.contains(curChar)) s.add(curChar);
				else {
					if(preChar==curChar) continue;
					else {
						result--;
						break;
					}
				}
			}
		}
		
		System.out.println(result);
	}
}
