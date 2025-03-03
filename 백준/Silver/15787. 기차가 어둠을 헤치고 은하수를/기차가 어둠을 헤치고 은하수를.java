import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] trains; //열차들 리스트를 저장할 배열
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trains = new int[N+1]; // N개만큼의 기차 생성 0으로 초기화
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int trainNum = Integer.parseInt(st.nextToken());
			
			switch(cmd) {
				case 1:
					int p = Integer.parseInt(st.nextToken());
					trains[trainNum] = trains[trainNum] | (int)Math.pow(2, p-1); // or 연산
					break;
				case 2:
					int m = Integer.parseInt(st.nextToken());
					// 특정 비트 0으로 마스킹
					int mask = ~(1<<(m-1));
					trains[trainNum] = trains[trainNum] & mask;
					break;
				case 3:
					// 왼쪽 시프트 연산 
					trains[trainNum] = (trains[trainNum] << 1) & ((1 << 20) - 1);
					break;
				case 4:
					// 오른쪽 시프트 연산
					trains[trainNum] = trains[trainNum]>>1;
					break;
			}
		}
		
		Set<Integer> result = new HashSet<>();

        for(int i = 1; i <= N; i++) {
            result.add(trains[i]); // 1번부터 N번 기차만 추가
        }

        System.out.println(result.size());
	}
}
