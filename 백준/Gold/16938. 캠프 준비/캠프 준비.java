import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, X;
	static int count = 0;
	static int[] problems;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		problems = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			problems[i] = Integer.parseInt(st.nextToken());
		}
		
		// 부분 집합 생성 후 경우의 수 구하기
		generateSubsets(0,0,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
		
		System.out.println(count);
	}
	
	// 매개변수 현재 문제의 인덱스, 인덱스 이전의 선택한 문제 갯수, 문제 난이도 합, 최고 난이도, 최소 난이도, 
	public static void generateSubsets(int idx, int selectCount, int total, int max, int min) {
		// 종료 조건(카운트 증가 조건)
		if(idx==N) { // idx가 끝까지 왔을때
			// 선택된 문제 수가 2개 이상에 난이도 합이 L이상 R이하이고 최대 난이도와 최소 난이도 차가 X이상일 때 경우의 수 증가
			if (selectCount >= 2 && total >= L && total <= R && max - min >= X) {
                count++;
            }
			return;
		}
		
		// 종료되지 않았다면 해당 문제를 선택했을때와 안했을때로 분기 
		// 해당 문제 선택 O
		generateSubsets(idx+1, selectCount+1, total+problems[idx], Math.max(max, problems[idx]), Math.min(min, problems[idx]));
		// 해당 문제 선택 X
		generateSubsets(idx+1, selectCount, total, max, min);
		
		
		
	}
}
