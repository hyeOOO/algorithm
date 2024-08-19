package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon5532 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());

		int kor = A % C != 0 ? A / C + 1 : A / C;
		int mat = B % D != 0 ? B / D + 1 : B / D;
		
		int result = L - Math.max(kor, mat);
		
		System.out.println(result);
	}
}
