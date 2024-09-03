
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제의 해를 단계별로 구성해나가야하는 '백트래킹' 문제
 * 1. 문제 상태 정의 : 스도쿠 입력 받기
 * 2. 종료 조건 정의 : 모든 숫자가 다 채워질 때
 * 3. 가능한 선택지에 대한 재귀 호출 : 1~9까지 유효범위 내에 숫자 넣기
 * 4. 백트래킹 : 현재 선택이 실패하면 이전 상태로 되돌리고 다른 선택지 시도하기
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] sudoku = new int[9][9];
		// 1. 스도쿠 입력 받기
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = str.charAt(j) - '0';
			}
		}

		if (backtrack(sudoku)) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
		}
	}

	public static boolean backtrack(int[][] sudoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// 2. 백트래킹 대상 찾기(빈칸 찾기)
				if (sudoku[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						// 숫자 삽입 시도
						if (check(sudoku, i, j, k)) {
							// 유효한 경우 다음 칸 채우기
							sudoku[i][j] = k;
							if (backtrack(sudoku)) {
								return true;
							}
						}
						// 해당 값을 0으로 만듬(0으로 만드는 이유: 유효하지 않거나 다음 값이 1~9까지 안들어갈 경우 이전 값에 대한 초기화하고 다른 숫자가 들어가야함)
						sudoku[i][j] = 0;
					}
					// 1부터 9까지 다 유효하지 않으면 이전 빈칸의 수정이 필요
					return false;
				}
			}
		}
		// 모든 경우를 다 돈 경우 true로 반환
		return true;
	}

	// 해당 위치의 행과 열 그리고 3x3 구역 내 유효성 검사
	public static boolean check(int[][] sudoku, int y, int x, int k) {
		// 행 검사
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][x] == k)
				return false;
		}

		// 열 검사
		for (int j = 0; j < 9; j++) {
			if (sudoku[y][j] == k)
				return false;
		}

		// 구역 검사
		int startY = (y / 3) * 3;
		int startX = (x / 3) * 3;

		for (int i = startY; i < startY + 3; i++) {
			for (int j = startX; j < startX + 3; j++) {
				if (sudoku[i][j] == k)
					return false;
			}
		}

		return true;
	}
}
