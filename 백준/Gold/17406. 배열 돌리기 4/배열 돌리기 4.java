
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] originalMap;
	static List<List<Integer>> oper;
	static List<List<Integer>> select;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		originalMap = new int[N][M];
		result = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				originalMap[i][j] = map[i][j];
			}
		}

		oper = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> operation = new ArrayList<>();
			operation.add(Integer.parseInt(st.nextToken())); // r
			operation.add(Integer.parseInt(st.nextToken())); // c
			operation.add(Integer.parseInt(st.nextToken())); // s
			oper.add(operation);
		}

		// 순열을 통해 연산 실행 순서 정하기
		visited = new boolean[K];
		select = new ArrayList<>();
		makePerm(0);

		System.out.println(result);
	}

	public static void makePerm(int depth) {
		if (depth == K) {
			// 선택한 순대로 연산을 실행
			applyOperations();
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 현재 선택된 연산을 리스트에 추가
				select.add(oper.get(i));
				// 다음 깊이로 순열 생성
				makePerm(depth + 1);
				// 백트래킹: 리스트에서 마지막으로 추가된 연산 제거
				select.remove(select.size() - 1);
				visited[i] = false;
			}
		}
	}

	public static void applyOperations() {
		// 원본 배열 복사
		for (int i = 0; i < N; i++) {
			map[i] = originalMap[i].clone();
		}

		for (List<Integer> list : select) {
			rotate(list.get(0), list.get(1), list.get(2));
		}

		calculate();
	}

	public static void rotate(int r, int c, int s) {

		int startY = r - s - 1;
		int startX = c - s - 1;
		int endY = r + s - 1;
		int endX = c + s - 1;

		while (startY < endY && startX < endX) {
			int temp = map[startY][startX];
			for (int i = startY; i < endY; i++)
				map[i][startX] = map[i + 1][startX];
			for (int i = startX; i < endX; i++)
				map[endY][i] = map[endY][i + 1];
			for (int i = endY; i > startY; i--)
				map[i][endX] = map[i - 1][endX];
			for (int i = endX; i > startX; i--)
				map[startY][i] = map[startY][i - 1];
			map[startY][startX + 1] = temp;

			startY++;
			startX++;
			endY--;
			endX--;
		}
	}

	public static void calculate() {
		for (int i = 0; i < N; i++) {
			int rowSum = 0;
			for (int j = 0; j < M; j++) {
				rowSum += map[i][j];
			}
			result = Math.min(result, rowSum);
		}
	}
}
