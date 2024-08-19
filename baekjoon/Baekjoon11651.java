// Coord가 아니라 Coord2인 이유: 같은 패키지 내 11650에 Coord 클래스가 이미 존재함;
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Coord2 implements Comparable<Coord2>{
	private int x;
	private int y;
	
	Coord2(int x, int y){
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
	public int compareTo(Coord2 o) {
		if(getY()!=o.getY()) return Integer.compare(getY(), o.getY());
		else return Integer.compare(getX(), o.getX());
	}
}

public class Baekjoon11651 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		List<Coord2> coords = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int x = Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			coords.add(new Coord2(x, y));
		}
		
		Collections.sort(coords);
		
		for(Coord2 c : coords) {
			sb.append(c.getX()).append(' ').append(c.getY()).append('\n');
		}
		
		System.out.println(sb);
	}
}
