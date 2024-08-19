// 1차 시도 : Set으로 풀었으나 시간초과 ^^.. 왜..
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon11723 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		Set<Integer> s = new HashSet<Integer>();
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String expr = st.nextToken();
			int num = 0;
			if(!expr.equals("all")&&!expr.equals("empty")) {
				num = Integer.parseInt(st.nextToken());
			}
			switch(expr) {
			case "add":
				s.add(num);
				break;
			case "remove":
				if(s.contains(num)) s.remove(num);
				break;
			case "check":
				if(s.contains(num)) sb.append(1).append("\n");
				else sb.append(0).append("\n");
				break;
			case "toggle":
				if(s.contains(num)) s.remove(num);
				else s.add(num);
				break;
			case "all":
				for(int j=1; j<=20; j++) {
					s.add(j);
				}
				break;
			case "empty":
				s.clear();
				break;
			}
		}
		
		System.out.println(sb);
	}
}
