import java.util.*;

class Solution {
    static char[][] map;

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        map = new char[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            boolean[][] remove = new boolean[m][n];
            boolean canRemove = false;

            // 2 x 2 블록 찾기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char block = map[i][j];

                    if (block == '0') continue;

                    if (map[i][j + 1] == block &&
                        map[i + 1][j] == block &&
                        map[i + 1][j + 1] == block) {

                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        remove[i + 1][j] = true;
                        remove[i + 1][j + 1] = true;

                        canRemove = true;
                    }
                }
            }

            if (!canRemove) break;

            // 블록 삭제
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (remove[i][j]) {
                        map[i][j] = '0';
                        answer++;
                    }
                }
            }

            // 블록 내리기
            for (int j = 0; j < n; j++) {
                // 살아남은 블록을 담을 임시 배열
                char[] stack = new char[m];
                int top = 0;

                // 아래에서 위로 올라가며 블록 수집
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != '0') {
                        stack[top++] = map[i][j];
                    }
                }

                // 위쪽부터 빈칸으로 초기화
                for (int i = 0; i < m; i++) {
                    map[i][j] = '0';
                }

                // 모아둔 블록을 아래부터 배치
                for (int i = 0; i < top; i++) {
                    map[m - 1 - i][j] = stack[i];
                }
            }
        }

        return answer;
    }
}