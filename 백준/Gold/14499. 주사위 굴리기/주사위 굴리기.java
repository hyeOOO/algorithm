import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] cmd = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> result = moveDice(startX, startY, map, cmd);

        for (int r : result) {
            System.out.println(r);
        }
    }

    public static List<Integer> moveDice(int startX, int startY, int[][] map, int[] cmd) {
        List<Integer> result = new ArrayList<>();
        int[] dice = new int[7]; // 주사위 면 배열
        int x = startX;
        int y = startY;

        // 동, 서, 북, 남 이동에 따른 좌표 변화
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        for (int i = 0; i < cmd.length; i++) {
            int nx = x + dx[cmd[i]];
            int ny = y + dy[cmd[i]];

            // 지도를 벗어나는 경우 명령 무시
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            // 주사위 굴리기
            rollDice(dice, cmd[i]);

            // 주사위 바닥면과 지도 칸 상호작용
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];
            } else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            // 주사위 윗면 값 결과에 추가
            result.add(dice[1]);

            // 현재 위치 갱신
            x = nx;
            y = ny;
        }

        return result;
    }

    public static void rollDice(int[] dice, int cmd) {
        int[] temp = dice.clone();
        switch (cmd) {
            case 1: // 동쪽
                dice[1] = temp[4];
                dice[3] = temp[1];
                dice[4] = temp[6];
                dice[6] = temp[3];
                break;
            case 2: // 서쪽
                dice[1] = temp[3];
                dice[3] = temp[6];
                dice[4] = temp[1];
                dice[6] = temp[4];
                break;
            case 3: // 북쪽
                dice[1] = temp[5];
                dice[2] = temp[1];
                dice[5] = temp[6];
                dice[6] = temp[2];
                break;
            case 4: // 남쪽
                dice[1] = temp[2];
                dice[2] = temp[6];
                dice[5] = temp[1];
                dice[6] = temp[5];
                break;
        }
    }
}
