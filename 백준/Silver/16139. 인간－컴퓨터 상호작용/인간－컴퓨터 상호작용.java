import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] chrArr = br.readLine().toCharArray();
		int[][] alphabetCount = new int[chrArr.length][26];

		alphabetCount[0][chrArr[0] - 'a']++;

		for (int i = 1; i < chrArr.length; i++) {
			// 이전 값 복사
			for (int j = 0; j < 26; j++) {
				alphabetCount[i][j] = alphabetCount[i - 1][j];
			}
			alphabetCount[i][chrArr[i] - 'a']++;
		}

		int q = Integer.parseInt(br.readLine());

		while (q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int alphaIdx = st.nextToken().charAt(0) - 'a';
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());

			if (idx1 != 0) {
				System.out.println(alphabetCount[idx2][alphaIdx] - alphabetCount[idx1 - 1][alphaIdx]);
			}else {
				System.out.println(alphabetCount[idx2][alphaIdx]);
			}
		}
	}
}
