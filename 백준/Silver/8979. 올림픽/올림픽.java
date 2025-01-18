import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Grade implements Comparable<Grade>{
		private int name;
		private int gold;
		private int silver;
		private int bronze;
		private int rank;
		
		public Grade(int name, int gold, int silver, int bronze) {
			super();
			this.name = name;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
			this.rank = 1;
		}

		@Override
		public int compareTo(Grade o) {
			if(this.gold == o.gold) {
				if(this.silver == o.silver) {
					return Integer.compare(o.bronze, this.bronze);
				}
				return Integer.compare(o.silver, this.silver);
			}
			return Integer.compare(o.gold, this.gold);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Grade[] grades = new Grade[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			grades[i] = new Grade(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(grades);
		
		for(int i=1; i<N; i++) {
			Grade cur = grades[i-1];
			Grade next = grades[i];
			
			if(cur.gold==next.gold && cur.silver == next.silver && cur.bronze == next.bronze) {
				next.rank = cur.rank;
			}else {
				next.rank = i+1;
			}
		}
		
		for(Grade grade : grades) {
			if(grade.name == K ) {
				System.out.println(grade.rank);
			}
		}
	}
}
