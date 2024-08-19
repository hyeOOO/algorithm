package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Coord implements Comparable<Coord>{
	private int x;
	private int y;
	
	Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public int compareTo(Coord o){
		if(getX()!=o.getX()) return Integer.compare(getX(), o.getX());
		else return Integer.compare(getY(), o.getY());
	}
}

public class Baekjoon11650 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Coord> coords= new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input, " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			coords.add(new Coord(x, y));
		}
		
		Collections.sort(coords);
		
		for(Coord c : coords) {
			System.out.println(c.getX()+" "+c.getY());
		}
	}
}
