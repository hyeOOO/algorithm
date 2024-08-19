// 인터넷보고 품.. 분발할것
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int input = Integer.parseInt(br.readLine());

		for (int i = 0; i < input; i++) {
			String str = br.readLine();
			st = new StringTokenizer(str, " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Queue<int[]> q = new LinkedList<>();

			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				q.add(new int[] { j, num });
			}
		
			int count = 0;
			while (true) {
				int[] current = q.poll();
				boolean flag = true;
				
				for(int[] next : q) {
					if(current[1]<next[1]) {
						flag = false;
						break;
					}
				}
				
				if(!flag) {
					q.add(current);
				}else {
					count++;
					if(current[0]==M) break;
				}
			}
			
			System.out.println(count);
		}
	}
}
