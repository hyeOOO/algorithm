package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon18110 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int cutPeople = (int) Math.round(N*0.15);
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		double total = 0;
		for(int i=cutPeople; i<arr.length-cutPeople; i++) {
			total += arr[i];
		}
		
		int a = (int) Math.round(total/(N-cutPeople*2));
		
		System.out.println(a);
		
	}
}
