

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @since 2024.08.22
 * @category BFS/DFS/구현
 */
public class Main {
	static int N, M, D;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// 1. N+1, M 배열 선언 후 입력 받기
		map = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2. N+1 행의 M개의 위치에서 임의로 3군데에 궁수를 놓는 조합 구하기
		makePerm(0, 0, new int[3]);

		System.out.println(max);
	}

	public static void makePerm(int depth, int start, int[] selected) {
		if (depth == 3) {
			startGame(selected);
			return;
		}

		for (int i = start; i < M; i++) {
			selected[depth] = i;
			makePerm(depth + 1, i + 1, selected);
		}
	}

	public static void startGame(int[] selected) {
		int[][] tempMap = new int[N + 1][M];
		int count = 0;

		// 초기 맵 복사
		for (int i = 0; i < N + 1; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, M);
		}

		while (true) {
			boolean[][] target = new boolean[N][M];
			// 3. 각 궁수마다 타겟을 선정
			for (int i = 0; i < 3; i++) {
				PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] != o2[2] ? Integer.compare(o1[2], o2[2])
						: o1[1] != o2[1] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

				int archerX = selected[i];

				for (int r = N - 1; r >= 0; r--) {
					for (int c = 0; c < M; c++) {
						int dist = Math.abs(N - r) + Math.abs(archerX - c);
						if (tempMap[r][c] == 1 && dist <= D) {
							pq.offer(new int[] { r, c, dist });
						}
					}
				}

				if (!pq.isEmpty()) {
					int[] targetEnemy = pq.poll();
					target[targetEnemy[0]][targetEnemy[1]] = true;
				}
			}

			// 4. 타겟 위치에 있는 괴물 제거
			boolean hasMonster = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (target[i][j]) {
						tempMap[i][j] = 0;
						count++;
					}
					if (tempMap[i][j] == 1) {
						hasMonster = true;
					}
				}
			}

			// 5. 괴물들 한 칸 씩 아래로 이동
			for (int i = N - 1; i > 0; i--) {
				System.arraycopy(tempMap[i - 1], 0, tempMap[i], 0, M);
			}
			for (int j = 0; j < M; j++) {
				tempMap[0][j] = 0;
			}

			if (!hasMonster)
				break;

		}
		max = Math.max(max, count);

	}

}
