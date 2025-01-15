import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 상태 (n, from, to) -> n개의 원판을 from 기둥에서 to 기둥으로 이동
// 점화식 result = (n-1, from, empty) + (1, from, to) + (n-1, emtpy, to) // 젤 밑 바닥을 제외하고 나머지를 빈 곳에 옮겨놓고, 젤 밑바닥을 to로 옮기고 나머지를 from으로 옮기는 방식 
// 단 (empty = 6-from-to) 기둥이 1,2,3 이 있으므로 비어있는 기둥 찾는 공식 

// 시간초과;

public class Main {
	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		
		
		if(N<=20) {
			hanoi(N,1,3,2);
			System.out.println(count);
			System.out.print(sb);
		}
	}

	public static void hanoi(int n, int from, int to, int empty) {
		if (n == 1) {
			count++;
			sb.append(from).append(" ").append(to).append("\n");
			return;
		}

		hanoi(n-1, from, empty, to);
		count++;
		sb.append(from).append(" ").append(to).append("\n");
		hanoi(n-1, empty, to, from);
		
	}
}
