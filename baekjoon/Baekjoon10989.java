//1차시도 : 시간초과 => 이유 : Scanner 사용 시 자체 정규식 검사 과정이 시간이 오래 소요되기 때문
//2차시도 : 시간초과 => 이유 : foreach문을 써서 arr배열 내 a를 그대로 println 할 시
//												, 매번 출력 스트림에 쓰기작업을 하기 때문에 성능에 영향을 줌
//												 그래서 append를 통해 줄바꿈인 '\n'을 삽입하여 한꺼번에 println을 하므로써 성능저하 최소화
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon10989 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr= new int[N];
		
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++) {
			sb.append(arr[i]).append('\n');
		}
		
		System.out.println(sb);
		
	}
}
