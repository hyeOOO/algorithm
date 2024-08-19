// 666 숫자 전위에 1부터 n까지의 경우랑 666후위에 1부터 n까지 붙였을 때의 경우랑 둘이 비교할 생각했는데 이런 방법이
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1436 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int num = 666;
		int count = 1;
		
		while(N!=count) {
			num ++;
			
			if(String.valueOf(num).contains("666")) count++;
		}
		
		System.out.println(num);
	}
}
