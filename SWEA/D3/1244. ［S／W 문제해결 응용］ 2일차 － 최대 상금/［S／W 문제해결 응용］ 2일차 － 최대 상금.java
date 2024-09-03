import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    static int changeChance;
    static int maxMoney;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());

            String[] str = st.nextToken().split("");
            List<Integer> money = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                money.add(Integer.parseInt(str[i]));
            }

            changeChance = Integer.parseInt(st.nextToken());
            maxMoney = 0;

            // 최대 depth 및 각 상태에 대해 방문 여부를 확인할 배열 생성
            boolean[][] visited = new boolean[changeChance + 1][1000000];  // 최대 6자리 숫자일 때, 숫자를 정수로 표현한 후 방문 체크

            dfs(money, 0, visited);

            System.out.printf("#%d %d\n", t, maxMoney);
        }
    }

    // 숫자 리스트를 정수로 변환하는 함수
    public static int convertToInt(List<Integer> money) {
        StringBuilder sb = new StringBuilder();
        for (int m : money) {
            sb.append(m);
        }
        return Integer.parseInt(sb.toString());
    }

    public static void dfs(List<Integer> money, int depth, boolean[][] visited) {
        // 현재 depth가 changeChance에 도달하면 최대값을 업데이트
        if (depth == changeChance) {
            maxMoney = Math.max(maxMoney, convertToInt(money));
            return;
        }

        // 현재 상태의 숫자를 정수로 변환
        int currentState = convertToInt(money);

        // 해당 상태에서 현재 depth에서 이미 방문했다면 종료
        if (visited[depth][currentState]) {
            return;
        }

        // 방문 표시
        visited[depth][currentState] = true;

        // 모든 가능한 쌍을 교환
        for (int i = 0; i < money.size(); i++) {
            for (int j = i + 1; j < money.size(); j++) {
                // i와 j의 숫자를 교환
                Collections.swap(money, i, j);
                dfs(money, depth + 1, visited);  // 다음 단계로 진행
                Collections.swap(money, i, j);  // 원상복구
            }
        }
    }
}
