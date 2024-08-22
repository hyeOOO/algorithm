import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] arr;
	public static int N;
	public static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		nQueen(0); 
		System.out.println(count);
	}

	public static void nQueen(int depth) {
		if (depth == N) { // 열을 다채우면 count 증가 후 리턴
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[depth] = i;

			if (possibility(depth)) { //depth열에서 i번째 행에 퀸을 놓을 수 있는지 검사
				nQueen(depth + 1);
			}
		}
	}

	public static boolean possibility(int n) {
		for (int i = 0; i < n; i++) {
			//해당 열의 행과 i열의 행이 일치할 경우(같은 행에 있을 경우)
			if (arr[i] == arr[n]) {
				return false;
				// 대각선 검사(열의 차와 행의 차가 같을 경우)
			} else if (Math.abs(n - i) == Math.abs(arr[n] - arr[i]))
				return false;
		}

		return true;
	}
}
