import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] words;
    static int N, K, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = 0;

        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new int[N];
        // 1. 각 단어를 a~z 비트마스킹 하기
        for (int i = 0; i < N; i++) {
            char[] cArr = br.readLine().toCharArray();
            int temp = 0;
            for (int j = 0; j < cArr.length; j++) {
                int c = cArr[j] - 'a';
                temp |= 1 << c;
            }
            words[i] = temp;
        }

        // 2. a/c/t/n/i를 제외하고 만들 수 있는 K-5개의 알파벳으로 만들 수 있는 모든 단어 구하기
        char[] alpha = new char[21];
        int index = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'a' || c == 'c' || c == 't' || c == 'n' || c == 'i')
                continue;
            alpha[index++] = c;
        }

        int initialMask = (1 << ('a' - 'a')) | (1 << ('c' - 'a')) | (1 << ('t' - 'a')) | (1 << ('n' - 'a')) | (1 << ('i' - 'a'));
        result = combination(alpha, 0, 0, initialMask, 0);
        System.out.println(result);
    }

    public static int combination(char[] alpha, int depth, int start, int mask, int count) {
        if (count == K - 5) {
            int readable = 0;
            for (int word : words) {
                if ((word & mask) == word) {
                    readable++;
                }
            }
            return readable;
        }

        if (depth == alpha.length || start >= alpha.length) {
            return 0;
        }

        int maxCount = 0;
        // 현재 알파벳을 선택하는 경우
        maxCount = Math.max(maxCount, combination(alpha, depth + 1, start + 1, mask | (1 << (alpha[start] - 'a')), count + 1));
        
        // 현재 알파벳을 선택하지 않는 경우
        maxCount = Math.max(maxCount, combination(alpha, depth + 1, start + 1, mask, count));

        return maxCount;
    }
}