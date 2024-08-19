package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon1764_1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		for(int j=0; j<M; j++) {
			String name = br.readLine();
			if(set.contains(name)) list.add(name);
		}
		
		Collections.sort(list);
		
		System.out.println(list.size());
		
		for(String s : list) {
			sb.append(s).append("\n");
		}
		
		System.out.println(sb);
	}
}
