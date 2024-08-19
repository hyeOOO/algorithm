package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon4949 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			String str = br.readLine();
			if (str.equals("."))
				break;
			
			if(isBalance(str)) sb.append("yes").append("\n");
			else sb.append("no").append("\n");
		}
		
		System.out.println(sb);
	}

	public static boolean isBalance(String str) {
		char[] arr = str.toCharArray();
		Stack<Character> s = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(')
				s.push('(');
			else if (arr[i] == '[')
				s.push('[');
			else if (arr[i] == ')') {
				if (!s.isEmpty() && s.peek() == '(')
					s.pop();
				else
					return false;
			} else if (arr[i] == ']') {
				if (!s.isEmpty() && s.peek() == '[')
					s.pop();
				else
					return false;
			}
		}
		
		if(s.isEmpty()) return true;
		else return false;
	}
}
