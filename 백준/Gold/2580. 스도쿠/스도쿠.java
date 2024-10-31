import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sudoku = new int[9][9];

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backtracking();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(sudoku[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	public static boolean backtracking() {
		for (int i = 0; i < 9; i++) { // y
			for (int j = 0; j < 9; j++) { // x
				if (sudoku[i][j] == 0) {
					for (int k = 1; k <= 9; k++) { // 넣을 값
						if (valid(i, j, k)) { // 만족하는 수에 대해서만 다음 0을 찾아서 검사
							sudoku[i][j] = k;
							if(backtracking()) {
								return true;
							}
						}
						
						sudoku[i][j]=0;
					}
					
					return false;
				}
			}
		}
		
		return true;

	}

	// 해당 좌표에 대해 가로, 세로, 3x3 구역 검사
	public static boolean valid(int y, int x, int val) {

		for (int i = 0; i < 9; i++) {
			if (sudoku[y][i] == val)
				return false;
		}

		for (int i = 0; i < 9; i++) {
			if (sudoku[i][x] == val)
				return false;
		}

		int startY = (y / 3) * 3;
		int startX = (x / 3) * 3;

		for (int i = startY; i < startY + 3; i++) {
			for (int j = startX; j < startX + 3; j++) {
				if (sudoku[i][j] == val)
					return false;
			}
		}

		return true;
	}
}
