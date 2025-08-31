import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Block> blocks;
	static Block nowB;
	static boolean map[][];
	static List<Integer> removeList;
	static int point;
	
	static class Block {
		int t;
		int x1;
		int y1;
		int x2;
		int y2;
		
		Block(int t, int x1, int y1, int x2, int y2) {
			this.t = t;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		blocks = new ArrayList<>();
		map = new boolean[10][10];
		
		int t,x,y;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			switch(t) {
			case 1:
				blocks.add(new Block(t, x, y, -1, -1));
				break;
			case 2:
				blocks.add(new Block(t, x, y, x, y+1));
				break;
			case 3:
				blocks.add(new Block(t, x, y, x+1, y));
				break;
			default:
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			nowB = blocks.get(i);
			moveG();
			moveB();
			checkPoint();
			checkPastel();
		}
		
		System.out.println(point);
		System.out.println(cntTile());
	}

	private static void checkPastel() {
		// G
		int x = 5;
		for(int y=0; y<=3; y++) {
			if(map[x][y]) {
				removeList.add(9); break;
			}
		}
		x = 4;
		for(int y=0; y<=3; y++) {
			if(map[x][y]) {
				removeList.add(8); break;
			}
		}
		removeG();
		removeList.clear();
		// B
		int y = 5;
		for(x=0; x<=3; x++) {
			if(map[x][y]) {
				removeList.add(9); break;
			}
		}
		y = 4;
		for(x=0; x<=3; x++) {
			if(map[x][y]) {
				removeList.add(8); break;
			}
		}
		removeB();
		removeList.clear();
	}

	private static int cntTile() {
		int cnt = 0; 
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(map[i][j]) cnt++;
			}
		}
		return cnt;
	}

	private static void checkPoint() {
		removeList = new ArrayList<Integer>();
		boolean pFlag = true;
		// G
		for(int x=9; x>=3; x--) {
			pFlag = true;
			for(int y=0; y<=3; y++) {
				if(!map[x][y]) pFlag = false;
			}
			if(pFlag) removeList.add(x);
		}
		removeG();
		point += removeList.size();
		removeList.clear();
		// B
		for(int y=9; y>=3; y--) {
			pFlag = true;
			for(int x=0; x<=3; x++) {
				if(!map[x][y]) pFlag = false;
			}
			if(pFlag) removeList.add(y);
		}
		removeB();
		point += removeList.size();
		removeList.clear();
	}

	private static void removeB() {
		int num = 0;
		for(int y : removeList) {
			y += num;
			for(int j=y; j>3; j--) {
				for(int x=0; x<=3; x++) {
					map[x][j] = map[x][j-1];
				}
			}
			num++;
		}
	}

	private static void removeG() {
		int num = 0;
		for(int x : removeList) {
			x+=num;
			for(int i=x; i>3; i--) {
				for(int y=0; y<=3; y++) {
					map[i][y] = map[i-1][y];
				}
			}
			num++;
		}
	}

	private static void moveG() {
		int row = 4;
		if(nowB.t == 1) {
			for(int x=4; x<=9; x++) {
				if(!map[x][nowB.y1])
					row = x;
				else
					break;
			}
			map[row][nowB.y1] = true;
		}
		if(nowB.t == 2) {
			for(int x=4; x<=9; x++) {
				if(!map[x][nowB.y1] && !map[x][nowB.y2])
					row = x;
				else
					break;
			}
			map[row][nowB.y1] = true;
			map[row][nowB.y2] = true;
		}
		if(nowB.t == 3) {
			for(int x=4; x<=9; x++) {
				if(!map[x][nowB.y1])
					row = x;
				else
					break;
			}
			map[row][nowB.y1] = true;
			map[row-1][nowB.y1] = true;
		}
	}
	
	private static void moveB() {
		int col = 4;
		if(nowB.t == 1) {
			for(int y=4; y<=9; y++) {
				if(!map[nowB.x1][y])
					col = y;
				else
					break;
			}
			map[nowB.x1][col] = true;
		}
		if(nowB.t == 2) {
			for(int y=4; y<=9; y++) {
				if(!map[nowB.x1][y]) 
					col = y;
				else
					break;
			}
			map[nowB.x1][col] = true;
			map[nowB.x1][col-1] = true;
		}
		if(nowB.t == 3) {
			for(int y=4; y<=9; y++) {
				if(!map[nowB.x1][y] && !map[nowB.x2][y])
					col = y;
				else
					break;
			}
			map[nowB.x1][col] = true;
			map[nowB.x2][col] = true;
		}
	}
}