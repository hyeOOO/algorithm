package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2072 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int j = 0; j < 10; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num % 2 == 0)
					continue;
				sum += num;
			}
			System.out.println("#"+(i)+" "+sum);
		}
	}
}
