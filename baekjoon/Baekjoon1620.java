// 1차 시도 : 시간초과 -> 퀴즈가 숫자인지 문자열인지 판별할 때 isDigit써서 시간초과가 났나?
// 2차  시도 : 시간초과 -> charAt(0)dl 49~57 사이인지 검사하는 방식으로 개선했으나 또 시간초과.. 포켓몬을 물어보면 for문을 또 도는게 문제인듯
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baekjoon1620 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, String> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();		
		
		for(int i=1; i<=N; i++) {
			String poketmon = br.readLine();
			map1.put(i, poketmon);
			map2.put(poketmon, i);
		}
		
		for(int j=0; j<M; j++) {
			String quiz = br.readLine();
			if(quiz.charAt(0)>=49 && quiz.charAt(0)<=57) {
				sb.append(map1.get(Integer.parseInt(quiz))).append("\n");
			}
			else {
				sb.append(map2.get(quiz)).append("\n");
			}
		}
		System.out.println(sb);
	}
}
