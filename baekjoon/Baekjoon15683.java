/**
 * 타입 3번 까먹음
 * 백트래킹 부재
 * 최소 사각지대 계산 시 각 타입에 대해서만 최솟값 선택함
 * 맵 복사 및 복원 부재
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Baekjoon15683 {
	public static int N, M;
	public static int[][] map;
	public static List<int[]> cctvList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					 cctvList.add(new int[]{i, j, map[i][j]});
			}
		}

		searchCCTV();
		int result=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) result++;
			}
		}
		
		System.out.println(result);
	}

	public static void searchCCTV() {
		for (int[] cctv : cctvList) {
			int y = cctv[0];
			int x = cctv[1];
			int type = cctv[2];
			
			if (type == 1) {
				int max = 0;
				int maxDirection = 0;
				if (max < countUp(y, x)) {
					max = countUp(y, x);
					maxDirection = 1;
				}
				if (max < countDown(y, x)) {
					max = countDown(y, x);
					maxDirection = 3;
				}
				if (max < countRight(y, x)) {
					max = countRight(y, x);
					maxDirection = 2;
				}
				if (max < countLeft(y, x)) {
					max = countLeft(y, x);
					maxDirection = 4;
				}

				if (maxDirection == 1) {
					chageUp(y, x);
				} else if (maxDirection == 2) {
					chageRight(y, x);
				} else if (maxDirection == 3) {
					chageDown(y, x);
				} else {
					chageLeft(y, x);
				}
			} else if (type == 2) {
				int max = countUp(y, x) + countDown(y, x);
				if (max < countRight(y, x) + countLeft(y, x)) {
					chageLeft(y, x);
					chageRight(y, x);
				} else {
					chageUp(y, x);
					chageDown(y, x);
				}
			} else if (type == 3) {
				int max = countLeft(y, x) + countUp(y, x) + countRight(y, x);
				int maxDirection = 1;
				if (max < countUp(y, x) + countRight(y, x) + countDown(y, x))
					maxDirection = 2;
				if (max < countRight(y, x) + countDown(y, x) + countLeft(y, x))
					maxDirection = 3;
				if (max < countDown(y, x) + countLeft(y, x) + countUp(y, x))
					maxDirection = 4;

				if (maxDirection == 1) {
					chageLeft(y, x);
					chageUp(y, x);
					chageRight(y, x);
				} else if (maxDirection == 2) {
					chageUp(y, x);
					chageRight(y, x);
					chageDown(y, x);
				} else if (maxDirection == 3) {
					chageRight(y, x);
					chageDown(y, x);
					chageLeft(y, x);
				} else {
					chageDown(y, x);
					chageLeft(y, x);
					chageUp(y, x);
				}
			} else {
				chageUp(y,x);
				chageRight(y, x);
				chageDown(y, x);
				chageLeft(y, x);
			}
		}
	}

	public static void chageUp(int y, int x) {
		for (int ny = 0; ny < y; ny++) {
			if (map[ny][x] == 6) break;
            if (map[ny][x] == 0) map[ny][x] = 9;
		}
	}

	public static void chageDown(int y, int x) {
		for (int ny = N - 1; ny > y; ny--) {
			if (map[ny][x] == 6) break;
            if (map[ny][x] == 0) map[ny][x] = 9;
		}
	}

	public static void chageLeft(int y, int x) {
		for (int nx = 0; nx < x; nx++) {
			if (map[y][nx] == 6) break;
            if (map[y][nx] == 0) map[y][nx] = 9;
		}
	}

	public static void chageRight(int y, int x) {
		for (int nx = M - 1; nx > x; nx--) { // 범위 벗어나려나
			if (map[y][nx] == 6) break;
            if (map[y][nx] == 0) map[y][nx] = 9;
		}
	}

	public static int countUp(int y, int x) {
		int count = 0;
		for (int ny = 0; ny < y; ny++) {
			if (map[ny][x] == 6) break;
			if (map[ny][x] == 0)
				count++;
		}
		return count;
	}

	public static int countDown(int y, int x) {
		int count = 0;
		for (int ny = N - 1; ny > y; ny--) {
			if (map[ny][x] == 6) break;
			if (map[ny][x] == 0)
				count++;
		}
		return count;
	}

	public static int countLeft(int y, int x) {
		int count = 0;
		for (int nx = 0; nx < x; nx++) {
			if (map[y][nx] == 6) break;
			if (map[y][nx] == 0)
				count++;
		}
		return count;
	}

	public static int countRight(int y, int x) {
		int count = 0;
		for (int nx = M - 1; nx > x; nx--) { 
			if (map[y][nx] == 6) break;
			if (map[y][nx] == 0)
				count++;
		}
		return count;
	}
}
