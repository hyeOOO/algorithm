import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] input1 = new int[26];
		int[] input2 = new int[26];
		int count = 0;
		
		for(char c : br.readLine().toCharArray()) {
			input1[c-'a']++;
		}
		
		for(char c : br.readLine().toCharArray()) {
			input2[c-'a']++;
		}
		
		for(int i=0; i<26; i++) {
			count += Math.abs(input1[i]-input2[i]);
		}
		
		System.out.println(count);
	}
}
