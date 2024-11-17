import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<String>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String person = st.nextToken();
			String cmd = st.nextToken();

			if (cmd.equals("enter")) {
				set.add(person);
			} else {
				if (set.size() > 0) {
					set.remove(person);
				}
			}
		}
		
		Iterator<String> iterSet = set.iterator();
		
		List<String> list = new ArrayList<String>();
		while(iterSet.hasNext()){
			list.add(iterSet.next());
		}
		
		Collections.sort(list, Collections.reverseOrder());
		
		for(String p : list) {
			System.out.println(p);
		}
	}
}
