import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Date 2024.09.04
 * @Note
 * [구현 방식]
 * 모든 가능한 조합이나 순열을 고려해야 하는 문제 -> 백트래킹
 * [구현 과정]
 * 1. N개의 무게추를 순서대로 놓기(순열)
 * 2. N개의 무게추를 순서대로 놨으면 해당 무게추들을 오른쪽 저울에 올릴 건지 왼쪽 저울에 올릴건지 시도
 * 3. 한 번이라도 저울이 왼쪽으로 기울면 해당 무게추를 왼쪽 저울에 놓는 선택 취소 
 * 4. N개의 무게추를 다 올렸는데도 오른쪽 저울이 무거우면 경우의 수 추가
 */
public class Solution {
	static int N;
	static int[] weight;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			weight = new int[N];
			result = 0;

			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}

			// 무게추를 먼저 나열해 놓고 각 무게추를 어느 저울에 놓을지 결정
			makePerm(0, new boolean[N], new int[N]);

			System.out.printf("#%d %d\n", t, result);
		}
	}

	// 1. N개의 무게추를 순서대로 놓기(순열)
	public static void makePerm(int depth, boolean[] visited, int[] select) {
		if (depth == N) {
			// 무게추를 다 나열했다면 각 무게추를 저울에 놓기
			backtrack(select, 0, 0, 0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				select[depth] = weight[i];
				makePerm(depth + 1, visited, select);
				visited[i] = false;
			}
		}
	}

	public static void backtrack(int[] select, int depth, int leftWeight, int rightWeight) {
		// 3. 한 번이라도 저울이 왼쪽으로 기울면 해당 무게추를 왼쪽 저울에 놓는 선택 취소 
		if (leftWeight < rightWeight)
			return;
		// 4. N개의 무게추를 다 올렸는데도 오른쪽 저울이 무거우면 경우의 수 추가
		if (depth == N) {
			result++;
			return;
		}

		// 2. N개의 무게추를 순서대로 놨으면 해당 무게추들을 오른쪽 저울에 올릴 건지 왼쪽 저울에 올릴건지 시도
		// 무게추를 왼쪽에 올릴 경우
		backtrack(select, depth + 1, leftWeight + select[depth], rightWeight);
		// 무게추를 오른쪽에 올릴 경우
		backtrack(select, depth + 1, leftWeight, rightWeight + select[depth]);

	}
}
