import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 보드탐색
// 2. 색종이 덮기 : 5x5 색종이부터 덮을 수 있는 확인 후 색종이 덮기(색종이 덮였으니 해당 map은 0으로 치환)
// 3. 재귀 탐색 : 다음 1 찾기
// 4. 백트래킹
// 5. 최소사용
// 6. 종료 조건 : 보드의 모든 1을 덮었거나, 더 이상 색종이를 덮을 수 없을 때 재귀를 종료
public class Main {
	static int[][] map;
	static int[] paperCount;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		paperCount = new int[] { 0, 5, 5, 5, 5, 5 };
		result = Integer.MAX_VALUE;

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		if (result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);

	}

	public static void dfs(int y, int x, int count) {
		// 모든 보드를 다 덮은 경우
        if (y == 10) {
            result = Math.min(result, count);
            return;
        }

		if (result <= count) // 가지치기(이미 result를 넘어섰으면 더이상 탐색x)
			return;

		// 다음 줄로 넘어가기
        if (x >= 10) {
            dfs(y + 1, 0, count);
            return;
        }

		//1. 보드탐색 : 1있는 칸 찾기
		if (map[y][x] == 1) {
			for (int i = 5; i >= 1; i--) {
				// 2. 색종이 덮기 전 유효성 체크
				if (paperCount[i] > 0 && isAttach(y, x, i)) {
					attach(y, x, i, 0); // 색종이 붙이기(1->0)
					paperCount[i]--;
					dfs(y, x + 1, count + 1); // 다음 위치 탐색
					attach(y, x, i, 1); // 색종이 떼기
					paperCount[i]++;
				}
			}
		} else {
			//오른쪽으로 이동
			dfs(y, x + 1, count);
		}
	}

	// 색종이 붙이기
	public static void attach(int y, int x, int size, int isAttach) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				map[i][j] = isAttach;
			}
		}
	}

	// 색종이 붙일 수 있나 확인
	public static boolean isAttach(int y, int x, int size) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (i < 0 || j < 0 || i >= 10 || j >= 10)
					return false;
				if (map[i][j] != 1)
					return false;
			}
		}
		return true;
	}
}
