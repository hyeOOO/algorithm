package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon10773 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<Integer>();
		
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num==0) s.pop();
			else s.push(num);
		}
		
		int total = 0;
		while(!s.isEmpty()) {
			total += s.pop();
		}
		
		System.out.println(total);
	}
}
