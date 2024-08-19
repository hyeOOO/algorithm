package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Swea6808 {
	public static int[] guyoung;
	public static List<Integer> inyoung;
	public static List<List<Integer>> permList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			inyoung = new ArrayList<Integer>();
			guyoung = new int[9];
			permList = new ArrayList<List<Integer>>();
			for (int i = 1; i <= 18; i++) {
				inyoung.add(i);
			}

			for (int i = 0; i < 9; i++) {
				guyoung[i] = Integer.parseInt(st.nextToken());
				inyoung.remove(Integer.valueOf(guyoung[i]));
			}

			makePermutation(0);
			
			int guyoungWin = 0;
			int inyoungWin = 0;
			for(List<Integer> list : permList) {
				int guyoungScore = 0, inyoungScore = 0;
				for(int i=0; i<9; i++) {
					if(list.get(i)<guyoung[i]) {
						guyoungScore += list.get(i)+guyoung[i];
					}
					else if(list.get(i)>guyoung[i]) {
						inyoungScore += list.get(i)+guyoung[i];
					}
				}
				
				if(guyoungScore>inyoungScore) guyoungWin++;
				else inyoungWin++;
			}
			
			System.out.printf("#%d %d %d\n",t, guyoungWin, inyoungWin );
		}
	}

	public static void makePermutation(int depth) {
		if(depth==inyoung.size()-1) { // depth가 0부터 시작했으니 8일때 종료
			permList.add(new ArrayList<>(inyoung));
			return;
		}
		
		for(int i=depth; i<inyoung.size(); i++) {
			Collections.swap(inyoung, depth, i);
			makePermutation(depth+1);
			Collections.swap(inyoung, depth, i);
		}
	}
}
