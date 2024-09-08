

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static List<CCTV> cctvList = new ArrayList<>();
	public static int answer = Integer.MAX_VALUE;
	public static int[] dy = { -1, 0, 1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };

	static class CCTV {
		int x, y, type;

		CCTV(int y, int x, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

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
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		
		dfs(0, map);
		System.out.println(answer);

	}

	public static void dfs(int index, int[][] currentMap) {
		if (index == cctvList.size()) {
			answer = Math.min(answer, countBlindSpot(currentMap));
			return;
		}

		CCTV cctv = cctvList.get(index);
		int[][] newMap = new int[N][M];

		switch (cctv.type) {
			case 1:
				for (int d = 0; d < 4; d++) {
					copyMap(currentMap, newMap);
					watch(newMap, cctv.y, cctv.x, d);
					dfs(index + 1, newMap);
				}
				break;
			case 2:
				for(int d=0; d<2; d++) {
					copyMap(currentMap, newMap);
					watch(newMap, cctv.y, cctv.x, d);
					watch(newMap, cctv.y, cctv.x, d+2);
					dfs(index+1, newMap);
				}
				break;
			case 3:
				for(int d=0; d<4; d++) {
					copyMap(currentMap, newMap);
					watch(newMap, cctv.y, cctv.x, d);
					watch(newMap, cctv.y, cctv.x, (d+1)%4);
					dfs(index+1, newMap);
				}
				break;
			case 4:
				for(int d=0; d<4; d++) {
					copyMap(currentMap, newMap);
					watch(newMap, cctv.y, cctv.x, d);
					watch(newMap, cctv.y, cctv.x, (d+1)%4);
					watch(newMap, cctv.y, cctv.x, (d+2)%4);
					dfs(index+1, newMap);
				}
				break;
			case 5:
				copyMap(currentMap, newMap);
				for(int d=0; d<4; d++) {
					watch(newMap, cctv.y, cctv.x, d);
				}
				dfs(index+1, newMap);
				break;
		}
	}

	public static void watch(int[][] map, int y, int x, int d) {
		while (true) {
			y += dy[d];
			x += dx[d];
			if (y < 0 || x < 0 || y >= N || x >= M || map[y][x] == 6)
				break;
			if (map[y][x] == 0)
				map[y][x] = -1;
		}
	}

	public static void copyMap(int[][] currentMap, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			System.arraycopy(currentMap[i], 0, newMap[i], 0, M);
		}
	}

	public static int countBlindSpot(int[][] map) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					count++;
			}
		}

		return count;
	}
}
