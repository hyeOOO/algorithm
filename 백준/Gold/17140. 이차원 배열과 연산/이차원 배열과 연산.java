import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static int[][] map;

	static class Number implements Comparable<Number> {
		int num;
		int frequency;

		Number(int n, int f) {
			this.num = n;
			this.frequency = f;
		}

		public int compareTo(Number n) {
			if (this.frequency != n.frequency) {
				return this.frequency - n.frequency;
			}
			return this.num - n.num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[3][3];
		int time = 0;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			if (time > 100) {
				System.out.println(-1);
				return;
			}
			if (map.length >= R && map[0].length >= C && map[R - 1][C - 1] == K) {
				System.out.println(time);
				return;
			}

			play();
			time++;
		}
	}

	public static void play() {
		int rSize = map.length;
		int cSize = map[0].length;
		if (rSize >= cSize) {
			rowExtension();
		} else {
			colExtension();
		}
	}

	public static void rowExtension() {
		List<List<Integer>> dynamic = new ArrayList<>();
		
		for(int i=0; i<map.length; i++) {
			dynamic.add(new ArrayList<>());
		}
		
		for (int i = 0; i < map.length; i++) {
			int[] row = map[i];
			List<Number> numbers = new ArrayList<>();
			int[] count = new int[101];
			for(int j=0; j<row.length; j++) {
				if(row[j]==0) continue;
				count[row[j]]++;
			}
			
			for(int j=1; j<=100; j++) { 
				if(count[j]==0) continue;
				numbers.add(new Number(j, count[j]));
			}
			
			Collections.sort(numbers);
			for(int j=0; j<numbers.size(); j++) {
				Number num = numbers.get(j);
				dynamic.get(i).add(num.num);
				dynamic.get(i).add(num.frequency);
			}
		}
		
		int maxCsize = 0;
		for(int i=0; i<dynamic.size(); i++) {
			maxCsize = Math.max(maxCsize, dynamic.get(i).size());
		}
		
//		convertArr(dynamic.size(), maxCsize, dynamic);
		
		map = new int[dynamic.size()][maxCsize];
		
		for(int i=0; i<dynamic.size(); i++) {
			for(int j=0; j<dynamic.get(i).size(); j++) {
				map[i][j] = dynamic.get(i).get(j);
			}
		}
	}

	public static void colExtension() {
		List<List<Integer>> dynamic = new ArrayList<>();
		
		for(int i=0; i<map[0].length; i++) {
			dynamic.add(new ArrayList<>());
		}
		
		for(int i=0; i<map[0].length; i++) {
			List<Number> numbers = new ArrayList<>();
			int[] count = new int[101];
			for(int j=0; j<map.length; j++) {
				if(map[j][i]==0) continue;
				count[map[j][i]]++;
			}
			
			for(int j=1; j<=100; j++) {
				if(count[j]==0) continue;
				numbers.add(new Number(j, count[j]));
			}
			
			Collections.sort(numbers);
			for(int j=0; j<numbers.size(); j++) {
				Number num = numbers.get(j);
				dynamic.get(i).add(num.num);
				dynamic.get(i).add(num.frequency);
			}
		}
		
		int maxRsize = 0;
		for(int i=0; i<dynamic.size(); i++) {
			maxRsize = Math.max(maxRsize, dynamic.get(i).size());
		}
		
//		convertArr(maxRsize, dynamic.size(), dynamic);
		
		map = new int[maxRsize][dynamic.size()];
		
		for(int i=0; i<dynamic.size(); i++) {
			for(int j=0; j<dynamic.get(i).size(); j++) {
				map[j][i] = dynamic.get(i).get(j);
			}
		}
	}
	
	public static void convertArr(int r, int c, List<List<Integer>> list) {
		map = new int[r][c];
		
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.get(i).size(); j++) {
				map[i][j] = list.get(i).get(j);
			}
		}
	}
}

