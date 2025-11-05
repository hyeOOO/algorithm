import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static int[][] events;
    static int[][] dp;
    static int[][] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        events = new int[W + 1][2];
        dp = new int[W + 1][W + 1];
        path = new int[W + 1][W + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        for (int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solve(0, 0);
        System.out.println(result);
        trace(0, 0);
    }

    static int solve(int car1, int car2) {
        int next = Math.max(car1, car2) + 1;
        if (next > W) return 0;
        if (dp[car1][car2] != -1) return dp[car1][car2];

        int fromX1 = (car1 == 0) ? 1 : events[car1][0];
        int fromY1 = (car1 == 0) ? 1 : events[car1][1];
        int fromX2 = (car2 == 0) ? N : events[car2][0];
        int fromY2 = (car2 == 0) ? N : events[car2][1];
        
        int dist1 = Math.abs(fromX1 - events[next][0]) + Math.abs(fromY1 - events[next][1]);
        int dist2 = Math.abs(fromX2 - events[next][0]) + Math.abs(fromY2 - events[next][1]);

        int moveCar1 = dist1 + solve(next, car2);
        int moveCar2 = dist2 + solve(car1, next);

        if (moveCar1 < moveCar2) {
            path[car1][car2] = 1;
            dp[car1][car2] = moveCar1;
        } else {
            path[car1][car2] = 2;
            dp[car1][car2] = moveCar2;
        }

        return dp[car1][car2];
    }

    static void trace(int car1, int car2) {
        int next = Math.max(car1, car2) + 1;
        if (next > W) return;

        int car = path[car1][car2];
        System.out.println(car);
        if (car == 1) trace(next, car2);
        else trace(car1, next);
    }
}