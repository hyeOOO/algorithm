// 1차 시도 : 시간초과..^^
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon1764 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<String> list = new ArrayList<>();
		int count = 0;
		
		for(int i=0; i<N+M; i++) {
			String name = br.readLine();
			if(list.contains(name)) {
				count++;
				sb.append(name).append("\n");
			}
			else list.add(name);
		}
		
		System.out.println(count);
		System.out.println(sb);
		
	}
}
