

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [백준/148891] 톱니바퀴
 * 
 * @since 2024.08.28
 */

// [풀이 과정]
// 1. 각 톱니바퀴의 정보 List 생성
// 2. 각 2번째 원소 (오른쪽 톱니바퀴와 맞닿는 부분)와 6번째 원소(왼쪽 톱니바퀴와 맞닿는 부분) 꺼내오기
// 3. 각 톱니바퀴를 회전시키기 전에 현재 2-6 원소의 극의 같음과 다름 정보를 isEqual 배열에 저장
// 4. 타겟 톱니바퀴를 회전 시키고 3번의 결과를 바탕으로 회전시키기
// 5. 타겟 톱니바퀴가 회전함으로 인해 퍼지는 톱니바퀴도 회전시키기

public class Main {
	static List<Integer>[] wheel = new ArrayList[4];
	static int[] rotationDirection = new int[4]; // 0: 회전 x, 1 : 시계, -1 : 반시계

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			wheel[i] = new ArrayList<>();
		}

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i].add(str.charAt(j) - '0');
			}
		}

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			isRotateWheel(target, direction);
			rotateWheel();
		}

		int result = 0;
		for (int i = 0; i < 4; i++) {
			int t = wheel[i].get(0);
			if (t == 1)
				result += (int) Math.pow(2, i);
		}

		System.out.println(result);
	}

	public static void isRotateWheel(int target, int direction) {
		for (int i = 0; i < 4; i++) {
			rotationDirection[i] = 0;
		}
		rotationDirection[target] = direction;

		// 오른쪽으로 전파
		for (int i = target; i < 3; i++) {
			if (wheel[i].get(2) != wheel[i + 1].get(6)) {
				rotationDirection[i+1] = -rotationDirection[i];
			} else
				break;
		}

		// 왼쪽으로 전파 ㅡㅡ
		for (int i = target; i > 0; i--) {
			if (wheel[i].get(6) != wheel[i - 1].get(2)) {
				rotationDirection[i-1] = -rotationDirection[i];
			} else
				break;
		}
	}

	public static void rotateWheel() {
		for (int i = 0; i < 4; i++) {
			if (rotationDirection[i] == 1) {
				wheel[i].add(0, wheel[i].remove(7));
			} else if (rotationDirection[i] == -1) {
				wheel[i].add(wheel[i].remove(0));
			}
		}
	}
}
