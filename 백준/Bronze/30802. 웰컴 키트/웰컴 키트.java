import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] size = new int[6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int shirt = 0, penSet = 0, pen = 0;
		for (int i = 0; i < 6; i++) {
			size[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		for (int s : size) {
			if (s % T == 0)
				shirt += s / T;
			else
				shirt += s / T + 1;
		}

		penSet = N / P;
		pen = N % P;

		System.out.println(shirt);
		System.out.printf("%d %d\n", penSet, pen);

	}
}
