import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> sendGift;
	static List<List<Integer>> receiveGift;
	static boolean[] result;  // 남아있는 사람을 표시하는 배열
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		sendGift = new ArrayList<>();
		receiveGift = new ArrayList<>();
		result = new boolean[N + 1];  // 기본값 false

		for (int i = 0; i <= N; i++) {
			sendGift.add(new ArrayList<>());
			receiveGift.add(new ArrayList<>());
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());

			sendGift.get(i).add(s1);
			sendGift.get(i).add(s2);

			receiveGift.get(s1).add(i);
			receiveGift.get(s2).add(i);

			result[i] = true;  // 모든 사람을 초기엔 true로 설정
		}

		Queue<Integer> queue = new LinkedList<>();

		// 받은 선물이 2개 미만인 사람을 큐에 넣음
		for (int i = 1; i <= N; i++) {
			if (receiveGift.get(i).size() < 2) {
				queue.add(i);
				result[i] = false;  // 제거 대상
			}
		}

		// BFS 방식으로 제거할 사람을 처리
		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (int send : sendGift.get(node)) {
				receiveGift.get(send).remove(Integer.valueOf(node));
				if (receiveGift.get(send).size() < 2 && result[send]) {
					queue.add(send);
					result[send] = false;  // 추가 제거 대상
				}
			}
		}

		int remaining = 0;
		for (int i = 1; i <= N; i++) {
			if (result[i]) {
				remaining++;
				sb.append(i).append(' ');
			}
		}

		if (remaining == 0) {
			System.out.println(0);
		} else {
			System.out.println(remaining);
			System.out.println(sb.toString());
		}
	}
}

