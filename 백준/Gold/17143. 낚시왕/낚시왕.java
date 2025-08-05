import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M, answer;
    static List<Shark> sharks = new ArrayList<>();
    static int[][] map;

    static class Shark {
        int y, x, s, d, z;
        boolean alive;

        Shark(int y, int x, int s, int d, int z, boolean alive) {
            this.y = y;
            this.x = x;
            this.s = s;
            this.d = d;
            this.z = z;
            this.alive = alive;
        }
    }

    static class Result {
        int pos, dir;
        Result(int pos, int dir) {
            this.pos = pos;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int[] row : map) Arrays.fill(row, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.add(new Shark(y, x, s, d, z, true));
            map[y][x] = i;
        }

        for (int i = 0; i < C; i++) {
            sharkCatch(i);
            sharkMove();
        }

        System.out.println(answer);
    }

    public static void sharkCatch(int col) {
        for (int row = 0; row < R; row++) {
            int idx = map[row][col];
            if (idx != -1 && sharks.get(idx).alive) {
                Shark s = sharks.get(idx);
                s.alive = false;
                answer += s.z;
                map[row][col] = -1;
                break;
            }
        }
    }

    public static void sharkMove() {
        int[][] newMap = new int[R][C];
        for (int[] row : newMap) Arrays.fill(row, -1);

        for (int i = 0; i < sharks.size(); i++) {
            Shark s = sharks.get(i);
            if (!s.alive) continue;

            int ny = s.y;
            int nx = s.x;
            int nd = s.d;

            if (nd == 1 || nd == 2) {
                Result r = bounceY(s.y, s.s, nd);
                ny = r.pos;
                nd = r.dir;
            } else {
                Result r = bounceX(s.x, s.s, nd);
                nx = r.pos;
                nd = r.dir;
            }

            if (newMap[ny][nx] != -1) {
                Shark other = sharks.get(newMap[ny][nx]);
                if (other.z > s.z) {
                    s.alive = false;
                } else {
                    other.alive = false;
                    s.y = ny;
                    s.x = nx;
                    s.d = nd;
                    newMap[ny][nx] = i;
                }
            } else {
                s.y = ny;
                s.x = nx;
                s.d = nd;
                newMap[ny][nx] = i;
            }
        }

        map = newMap;
    }

    public static Result bounceY(int y, int s, int d) {
        int len = (R - 1) * 2;
        s %= len;
        for (int i = 0; i < s; i++) {
            if (d == 1) { // 위
                if (y == 0) {
                    d = 2;
                    y++;
                } else {
                    y--;
                }
            } else { // 아래
                if (y == R - 1) {
                    d = 1;
                    y--;
                } else {
                    y++;
                }
            }
        }
        return new Result(y, d);
    }

    public static Result bounceX(int x, int s, int d) {
        int len = (C - 1) * 2;
        s %= len;
        for (int i = 0; i < s; i++) {
            if (d == 4) { // 왼쪽
                if (x == 0) {
                    d = 3;
                    x++;
                } else {
                    x--;
                }
            } else { // 오른쪽
                if (x == C - 1) {
                    d = 4;
                    x--;
                } else {
                    x++;
                }
            }
        }
        return new Result(x, d);
    }
}
