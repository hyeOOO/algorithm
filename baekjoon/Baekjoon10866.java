package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjoon10866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input, " ");
			switch(st.nextToken()) {
			case "push_front" :
				dq.addFirst(Integer.parseInt(st.nextToken()));
				break;
			case "push_back" :
				dq.addLast(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front" :
				if(!dq.isEmpty()) sb.append(dq.pollFirst());
				else sb.append(-1);
				sb.append("\n");
				break;
			case "pop_back" :
				if(!dq.isEmpty()) sb.append(dq.pollLast());
				else sb.append(-1);
				sb.append("\n");
				break;
			case "size" :
				sb.append(dq.size());
				sb.append("\n");
				break;
			case "empty" :
				if(!dq.isEmpty()) sb.append(0);
				else sb.append(1);
				sb.append("\n");
				break;
			case "front" :
				if(!dq.isEmpty()) sb.append(dq.peekFirst());
				else sb.append(-1);
				sb.append("\n");
				break;
			case "back" :
				if(!dq.isEmpty()) sb.append(dq.peekLast());
				else sb.append(-1);
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
}
