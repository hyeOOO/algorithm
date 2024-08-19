package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * [풀이 계획]
 * 1. 입력 받기
 * 2. 약품처리가 필요한 지 체크하기
 * 3. 약품처리가 필요하면 약품 처리하면서 백트래킹
 * 
 */
public class Swea2112 {
	static int[] layerA, layerB;
	static int D, W, K;
	static int[][] arr;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			layerA = new int[W];
			layerB = new int[W];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < W; i++) {
				layerA[i] = 0;
			}

			for (int i = 0; i < W; i++) {
				layerB[i] = 1;
			}

			process(0, 0);

			System.out.printf("#%d %d\n", t, min);
		}

	}

	public static void process(int row, int cnt) {
		if (row == D) {
			if (check()) {
				if (cnt < min)
					min = cnt;
			}
			return;
		}

		if (cnt >= min)
			return;

		int[] tmp = arr[row];

		process(row + 1, cnt);

		arr[row] = layerA;
		process(row + 1, cnt + 1);

		arr[row] = layerB;
		process(row + 1, cnt + 1);

		arr[row] = tmp;
	}
	
	public static boolean check() {
		for(int c=0; c<W; c++) {
			int cnt = 1;
			int max = 0;
			for(int r=0; r<D-1; r++) {
				if(arr[r][c] == arr[r+1][c]) {
					cnt++;
				}else {
					cnt = 1;
				}
				
				if(cnt>max) max = cnt;
			}
			if(max<K) return false;
		}
		return true;
	}
}
