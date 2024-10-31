import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, max;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		list = new ArrayList<Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		backTrack(0);
		
		System.out.println(max);
	}

	public static void backTrack(int totalEnergy) {
		if(list.size()==2) { //2개만 남을 경우 max 값 구해야함.
			max = Math.max(max, totalEnergy); // 최대 에너지 구하기
			return;
		}
		
		for(int i=1; i<list.size()-1; i++) {
			int temp = list.get(i);
			int energy = list.get(i-1)*list.get(i+1); //양 옆의 에너지를 모으고
			list.remove(i); // 해당 위치 구슬은 없애고
			backTrack(totalEnergy+energy); // 축적된 에너지에 현재 에너지를 더하고
			list.add(i, temp); // 다시 구슬 재생시키기
		}
	}
}
