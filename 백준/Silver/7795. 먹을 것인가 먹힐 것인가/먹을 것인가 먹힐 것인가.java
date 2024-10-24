import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arrA = new int[N];
			int[] arrB = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arrB[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arrA);
			Arrays.sort(arrB);

			int count = 0;

			for (int i = 0; i < arrA.length; i++) {
				int mid = -1;
				int left = 0, right = arrB.length;

				while (left <= right) {
					mid = (left + right) / 2;
					if (left == right)
						break;

					if (arrA[i] > arrB[mid]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}

				count += mid;
			}

			System.out.println(count);
		}
	}
}
