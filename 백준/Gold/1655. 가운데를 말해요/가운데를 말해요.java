import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minH = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxH = new PriorityQueue<Integer>(Comparator.reverseOrder());
		
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			if(i%2==0) maxH.add(n);
			else minH.add(n);
			
			while(!minH.isEmpty()&&!maxH.isEmpty()&&minH.peek()<maxH.peek()) {
				int min = minH.remove(); // 큰 주머니에서 가장 작은 값 뽑아오기
				int max = maxH.remove(); // 작은 주머니에서 가장 큰 값 뽑아오기
				
				minH.add(max);
				maxH.add(min);
			}
			
			System.out.println(maxH.peek());
		}
		
	}
}
