import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chrArr = br.readLine().toCharArray();
        int[][] prefixSum = new int[26][chrArr.length]; // 알파벳 26개에 대해 각 인덱스까지의 누적합 저장

        // 누적합 배열 초기화
        prefixSum[chrArr[0] - 'a'][0] = 1;
        for (int i = 1; i < chrArr.length; i++) {
            for (int j = 0; j < 26; j++) {
                prefixSum[j][i] = prefixSum[j][i - 1]; // 이전 값 복사
            }
            prefixSum[chrArr[i] - 'a'][i]++; // 현재 문자의 카운트 증가
        }

        int q = Integer.parseInt(br.readLine()); // 질문 개수
        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int alphaIdx = st.nextToken().charAt(0) - 'a';
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            if (idx1 == 0) {
                sb.append(prefixSum[alphaIdx][idx2]).append("\n");
            } else {
                sb.append(prefixSum[alphaIdx][idx2] - prefixSum[alphaIdx][idx1 - 1]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
