package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon2751 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		Set<Integer> sets = new HashSet<>();
		for(int i = 0; i<n; i++) {
			sets.add(Integer.parseInt(br.readLine()));
		}
		Integer[] arr= sets.toArray(new Integer[0]);
		
		Arrays.sort(arr);
		
		for(int i=0; i<n; i++) {
			sb.append(arr[i]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
