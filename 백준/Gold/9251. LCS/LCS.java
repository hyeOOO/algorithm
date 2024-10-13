import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [풀이 계획]
 * 1. 첫 번째 문자열 x 두 번째 문자열 크기만큼의 dp 배열 생성
 * 2. 젤 끝 문자열(str1.length-1, str2.lenght-1)부터 시작
 * 	2-1. 두 문자열이 같으면 dp[i][j] = dp[i-1][j-1]+1
 *  2-2. 다르면 dp[i][j] = dp[i-1][j]와 dp[i][j-1]중 큰 값
 * 3. 제일 마지막 값(dp[str1.length-1][str2.length-2]) return
 */
public class Main {
	static char[] str1;
	static char[] str2;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();

		dp = new int[str1.length][str2.length];

		// DP 테이블을 -1로 초기화하여 아직 계산되지 않은 값을 나타냄
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				dp[i][j] = -1;
			}
		}

		int result = lcs(str1.length - 1, str2.length - 1);

		System.out.println(result);
	}

	public static int lcs(int y, int x) {
		if (y < 0 || x < 0)
			return 0;

		// 이미 계산된 값이면 그대로 반환
		if (dp[y][x] != -1) {
			return dp[y][x];
		}
		
		// 두 문자열이 같으면 dp[i][j] = dp[i-1][j-1]+1
		if (str1[y] == str2[x]) {
			dp[y][x] = lcs(y - 1, x - 1) + 1;
		} else { // 다르면 dp[i][j] = dp[i-1][j]와 dp[i][j-1]중 큰 값
			dp[y][x] = Math.max(lcs(y - 1, x), lcs(y, x - 1));
		}
		return dp[y][x];
	}
}
