package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon20529 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// 비둘기집 원리
			if (N > 32)
				System.out.println(0);
			else {
				String[] mbti = new String[N];
				for (int i = 0; i < N; i++) {
					mbti[i] = st.nextToken();
				}

				int min = Integer.MAX_VALUE;

				for (int i = 0; i < N; i++) {
					for (int j = i + 1; j < N; j++) {
						for (int k = j + 1; k < N; k++) {
							int count = 0;
							for (int m = 0; m < 4; m++) {
								if (mbti[i].charAt(m) != mbti[j].charAt(m))
									count++;
								if (mbti[j].charAt(m) != mbti[k].charAt(m))
									count++;
								if (mbti[i].charAt(m) != mbti[k].charAt(m))
									count++;
							}

							min = Math.min(count, min);
							if (min == 0)
								break;
						}
					}
				}
				System.out.println(min);
			}
		}
	}
}
