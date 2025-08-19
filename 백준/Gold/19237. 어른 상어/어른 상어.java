import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<Shark> sharks = new ArrayList<>();
    static int[][] map;
    static int[][] pTime;
    static int[][] pShark;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Shark {
        int level;
        int y;
        int x;
        int d;
        int[][] priorityMove;

        Shark(int level, int y, int x, int d, int[][] move) {
            this.level = level;
            this.y = y;
            this.x = x;
            this.d = d;
            this.priorityMove = move;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pTime = new int[N][N];
        pShark = new int[N][N];
        int currentTime = 0;

        // 상어 시작 방향 저장
        int[] startDirection = new int[M];

        // 맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 상어의 시작 방향 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            startDirection[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 모든 상어의 방향 우선순위를 미리 저장
        int[][][] allMoves = new int[M][4][4];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    allMoves[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        // 맵을 스캔해서 상어 생성 (올바른 우선순위 할당)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    int sharkNum = map[i][j];
                    sharks.add(new Shark(sharkNum, i, j, startDirection[sharkNum-1], allMoves[sharkNum-1]));
                    pTime[i][j] = K;
                    pShark[i][j] = sharkNum;
                }
            }
        }

        while (currentTime <= 1000) {
            if (sharks.size() == 1 && sharks.get(0).level == 1) {
                break;
            }

            // 1. 임시 배열에 상어 이동 결과 저장
            int[][] tempMap = new int[N][N];
            moveShark(tempMap);

            // 2. 겹치는 상어 제거 (임시 배열에서)
            sharkCheck(tempMap);

            // 3. 페로몬 시간 감소
            decreasePheromone();

            // 4. 새 위치에 페로몬 추가
            markPheromone(tempMap);

            currentTime++;
        }

        if (currentTime > 1000) {
            System.out.println(-1);
            return;
        }

        System.out.println(currentTime);
    }

    public static void decreasePheromone() {
        // 기존 페로몬 시간 감소
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pTime[i][j] > 0) pTime[i][j]--;
                if (pTime[i][j] == 0) pShark[i][j] = 0;
            }
        }
    }

    public static void markPheromone(int[][] tempMap) {
        // 임시 배열에서 상어가 있는 위치에 페로몬 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j] > 0) {
                    pTime[i][j] = K;
                    pShark[i][j] = tempMap[i][j];
                }
            }
        }
    }

    public static void sharkCheck(int[][] tempMap) {
        // 임시 맵에서 겹치는 상어들 처리
        Map<String, List<Shark>> positionMap = new HashMap<>();

        for (Shark shark : sharks) {
            String pos = shark.y + "," + shark.x;
            positionMap.computeIfAbsent(pos, k -> new ArrayList<>()).add(shark);
        }

        Set<Integer> toRemove = new HashSet<>();

        for (List<Shark> sharksAtPos : positionMap.values()) {
            if (sharksAtPos.size() > 1) {
                // 가장 작은 번호의 상어만 남기고 나머지 제거
                int minLevel = sharksAtPos.stream().mapToInt(s -> s.level).min().orElse(Integer.MAX_VALUE);
                for (Shark s : sharksAtPos) {
                    if (s.level != minLevel) {
                        toRemove.add(s.level);
                        tempMap[s.y][s.x] = 0; // 임시 맵에서도 제거
                    }
                }
                // 살아남은 상어만 임시 맵에 표시
                tempMap[sharksAtPos.get(0).y][sharksAtPos.get(0).x] = minLevel;
            } else {
                // 겹치지 않는 상어는 그대로 임시 맵에 표시
                Shark s = sharksAtPos.get(0);
                tempMap[s.y][s.x] = s.level;
            }
        }

        // 실제 상어 리스트에서 제거
        sharks.removeIf(s -> toRemove.contains(s.level));

        // 맵 업데이트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }

    public static void moveShark(int[][] tempMap) {
        for (Shark shark : sharks) {
            int y = shark.y;
            int x = shark.x;
            int d = shark.d;
            int py = -1;
            int px = -1;
            int pd = -1;
            int[][] move = shark.priorityMove;
            boolean isMove = false;
            boolean flag = false;

            // 우선순위에 따라 이동할 곳 찾기
            for (int i = 0; i < 4; i++) {
                int nd = move[d][i];
                int ny = y + dy[nd];
                int nx = x + dx[nd];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

                if (pTime[ny][nx] == 0) {
                    // 냄새가 없을 때 - 최우선
                    y = ny;
                    x = nx;
                    d = nd;
                    isMove = true;
                    break;
                }

                // 자신의 냄새가 있는 곳을 백업으로 저장
                if (!flag && pShark[ny][nx] == shark.level) {
                    py = ny;
                    px = nx;
                    pd = nd;
                    flag = true;
                }
            }

            if (!isMove) {
                // 빈칸으로 움직이지 못하면 자기 냄새가 있는 곳으로 이동
                if (py != -1 && px != -1) {
                    y = py;
                    x = px;
                    d = pd;
                }
                // 만약 자기 냄새도 없다면 그대로 있음 (실제로는 발생하지 않아야 함)
            }

            // 상어 위치와 방향 업데이트
            shark.y = y;
            shark.x = x;
            shark.d = d;

            // 임시 맵에서 충돌 처리 (나중에 sharkCheck에서 정리)
            if (tempMap[y][x] == 0) {
                tempMap[y][x] = shark.level;
            } else {
                // 이미 다른 상어가 있다면 번호가 작은 상어가 살아남도록 처리
                tempMap[y][x] = Math.min(tempMap[y][x], shark.level);
            }
        }
    }
}