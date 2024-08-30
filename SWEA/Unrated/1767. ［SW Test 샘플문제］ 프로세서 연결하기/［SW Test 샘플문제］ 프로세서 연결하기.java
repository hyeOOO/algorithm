import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
    static int N, maxCoreCount, minLength;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static List<int[]> cores;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			maxCoreCount = 0; // 초기값을 0으로 설정해야함
			minLength = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1) {
						cores.add(new int[] { i, j });
					}
				}
			}

			dfs(0, 0, 0);

			System.out.println("#" + t + " " + minLength);
		}
	}
    
    public static void dfs(int depth, int coreCnt, int cableLen) {
		// 코어를 모두 처리했을 때
		if (depth == cores.size()) {
			if (coreCnt > maxCoreCount || (coreCnt == maxCoreCount && cableLen < minLength)) {
				maxCoreCount = coreCnt; // maxCoreCount를 올바르게 갱신
				minLength = cableLen; // minLength를 올바르게 갱신
			}
			return;
		}

		// cores 내 core를 가져와서 dfs하기
		int[] core = cores.get(depth);
		int y = core[0];
		int x = core[1];

		for (int d = 0; d < 4; d++) {
			int ny = y;
			int nx = x;
			int len = 0;

			while (true) {
				ny += dy[d];
				nx += dx[d];

				// 배열 끝에 도착하면 빠져나오기
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					break;

				// 탐색 중 전선이나 코어를 만난 경우 해당 방향으로는 더이상 진행 불가
				if (map[ny][nx] > 0) {
					len = 0;
					break;
				}

				len++;
			}

			// 해당 전선이 유효할 때
			if (len > 0) {
				ny = y;
				nx = x;

				// 전선 처리
				for (int i = 0; i < len; i++) {
					ny += dy[d];
					nx += dx[d];
					map[ny][nx] = 2;
				}

				// 다음 코어 탐색, 해당 코어는 전선연결 처리 완료, 해당 코어의 전선 길이 가중
				dfs(depth + 1, coreCnt + 1, cableLen + len);

				// 연결된 전선 해제
				ny = y;
				nx = x;
				for (int i = 0; i < len; i++) {
					ny += dy[d];
					nx += dx[d];
					map[ny][nx] = 0;
				}
			}
		}

		// 해당 코어를 연결하지 않는 경우
		dfs(depth + 1, coreCnt, cableLen);
	}
}