import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			PriorityQueue<Integer> minH = new PriorityQueue<Integer>();
			PriorityQueue<Integer> maxH = new PriorityQueue<Integer>(Comparator.reverseOrder());
			int answer = 0;
			
			minH.add(A);
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				if(n1>n2) {
					minH.add(n1);
					maxH.add(n2);
				}else {
					minH.add(n2);
					maxH.add(n1);
				}
				
				while(minH.peek()<maxH.peek()) {
					int min = minH.remove();
					int max = maxH.remove();
					
					minH.add(max);
					maxH.add(min);
				}
				
				answer = (minH.peek()+answer)%20171109;
				
			}
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
}