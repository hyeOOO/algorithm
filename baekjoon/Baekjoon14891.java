package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon14891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Deque<Integer>> wheels = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String[] token = br.readLine().split("");
            Deque<Integer> wheel = new ArrayDeque<Integer>();
            for (int j = 0; j < token.length; j++) {
                wheel.add(Integer.parseInt(token[j]));
            }
            wheels.add(wheel);
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[4];
            rotateWheels(wheels, num - 1, direction, visited);
        }

        int result = 0;
        if (wheels.get(0).getFirst() == 1) result += 1;
        if (wheels.get(1).getFirst() == 1) result += 2;
        if (wheels.get(2).getFirst() == 1) result += 4;
        if (wheels.get(3).getFirst() == 1) result += 8;

        System.out.println(result);
    }

    public static int[] getLeftRight(Deque<Integer> wheel) {
        Iterator<Integer> iter = wheel.iterator();
        int[] elements = new int[8];
        for (int i = 0; i < 8; i++) {
            elements[i] = iter.next();
        }
        int right = elements[2];
        int left = elements[6];

        return new int[]{right, left};
    }

    public static void rotateWheels(List<Deque<Integer>> wheels, int num, int direction, boolean[] visited) {
        visited[num] = true;
        int[] currentWheelSides = getLeftRight(wheels.get(num));

        // 현재 톱니바퀴 회전
        moveWheel(wheels.get(num), direction);

        // 왼쪽 톱니바퀴 검사
        if (num > 0 && !visited[num - 1]) {
            int[] leftWheelSides = getLeftRight(wheels.get(num - 1));
            if (currentWheelSides[1] != leftWheelSides[0]) {
                rotateWheels(wheels, num - 1, -direction, visited);
            }
        }

        // 오른쪽 톱니바퀴 검사
        if (num < 3 && !visited[num + 1]) {
            int[] rightWheelSides = getLeftRight(wheels.get(num + 1));
            if (currentWheelSides[0] != rightWheelSides[1]) {
                rotateWheels(wheels, num + 1, -direction, visited);
            }
        }
    }

    public static void moveWheel(Deque<Integer> wheel, int direction) {
        if (direction == 1) { // 시계방향
            int last = wheel.pollLast();
            wheel.addFirst(last);
        } else { // 반시계방향
            int first = wheel.pollFirst();
            wheel.addLast(first);
        }
    }
}
