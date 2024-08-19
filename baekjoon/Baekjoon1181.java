// compare 오버라이딩 하는거 까먹어서 인터넷 참고
// 1차 시도 : 런타임에러 => StringBuilder 사용했는데 런타임떠서 걍 foreach씀
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon1181 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Set<String> sets = new HashSet<>();
		
		for(int i=0; i<n; i++) {
			sets.add(br.readLine());
		}
		
		String[] arr = sets.toArray(new String[0]);
		
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.length() != o2.length()) // 길이가 다르면 길이 순으로 비교
                    return Integer.compare(o1.length(), o2.length());
				else return o1.compareTo(o2);
			}
		});
		
		for(String a : arr) {
			System.out.println(a);
		}
	}
}
