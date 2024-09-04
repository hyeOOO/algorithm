import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static List<int[]> slither;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			slither = new ArrayList<int[]>();
			result = Long.MAX_VALUE;

			for (int n = 0; n < N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				// 1. 지렁이 위치 좌표 입력 받기
				slither.add(new int[] { x, y });
			}

			// 2. 매칭되는 지렁이쌍의 Index를 저장하는 selected 배열 선언 
			boolean[] selected = new boolean[N];

			matchSlither(0, 0, selected);
			
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		
		System.out.println(sb.toString());
	}

	public static void matchSlither(int depth, int start, boolean[] selected) {
		// 4. 한 그룹의 조합이 완성된 경우(depth==N/2)
		if (depth == N / 2) {
			// 최소 백터 차이 계산
			result = Math.min(result, getDistance(selected));
			return;
		}

		// 3. 지렁이를 선택하는 지렁이로 둘 건지 아님 비선택 지렁이로 할건지 백트래킹하면서 조합 생성
		for (int i = start; i < N; i++) {
			if (!selected[i]) {
				// 해당 지렁이 선택
				selected[i] = true;
				// 다음 조합 생성 
				matchSlither(depth + 1, i + 1, selected);
				// 백트래킹 : 해당 지렁이 비선택
				selected[i] = false;
			}
		}
	}

	// 5. 최소 벡터 차이 제곱 합 구하기
	public static long getDistance(boolean[] selected) {
		long sumX = 0;
		long sumY = 0;
		
		// 선택된 지렁이들과 선택되지 않은 지렁이들 간의 매칭하여 벡터 값 계산
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				sumX += slither.get(i)[0];
				sumY += slither.get(i)[1];
			} else {
				sumX -= slither.get(i)[0];
				sumY -= slither.get(i)[1];
			}
		}

		return sumX * sumX + sumY * sumY;
	}
}
