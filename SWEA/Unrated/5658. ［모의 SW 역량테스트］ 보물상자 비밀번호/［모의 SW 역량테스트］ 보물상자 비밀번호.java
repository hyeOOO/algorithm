import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

// [해결 과정]
// 1. 문자열을 입력받고 
// 2. 맨 뒷 원소를 젤 앞으로 가져오기 
// 3. 그리고 원소를 N/4개씩 잘라서 리스트에 넣기(리스트에 이미 존재 시 안넣어도 됨)
// 4. 2,3 번 과정을 N/4번까지 반복
// 5. 오름차순 정렬
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			List<String> list = new ArrayList<String>();

			int len = N / 4;
			StringBuilder sb = new StringBuilder(br.readLine());

			// 문자열 잘라서 넣기
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < 4; j++) {
					String num = sb.substring(j * len, j * len + len);
					if (!list.contains(num)) {
						list.add(num);
					}
				}

				//문자열 맨 끝 문자 뽑아내서 앞에 추가하기
				char c = sb.charAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
				sb.insert(0, c);
			}

			Collections.sort(list, (o1, o2) -> Integer.compare(Integer.parseInt(o2, 16), Integer.parseInt(o1, 16)));
			
			String result = list.get(K-1);
			
			System.out.printf("#%d %d\n",t,Integer.parseInt(result, 16));

		}
	}
}
