

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @since 2024.08.27 [백준/17471번]게리맨더링
 */
// 1. 인접 리스트를 만들어서 노드별 인접 노드 저장
// 2. 인접 리스트를 통해 만들 수 있는 부분 집합 구하기
//		2-1. A집합과 B집합을 통해 A집합에 안속하면 B집합에 속하도록 로직 구현
//		2-2. A집합이 NULL이거나 B집합이 NULL 이면 X
// 3. A집합 내 노드들이 전부 연결됐는지, B집합도 마찬가지로 연결되었는지 확인
// 4. 모두 연결되어 있다면 최소 인구차 구하기
public class Main {
	static int N;
	static List<List<Integer>> abjList;
	static int[] peoples;
	static boolean[] visited;
	static boolean[] isSelected;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		peoples = new int[N];
		isSelected = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}

		abjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			abjList.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken());
				abjList.get(i).add(num - 1);
			}
		}

		divide(0);
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}

	private static void divide(int depth) {
		if (depth == N) {
			List<Integer> listA = new ArrayList<Integer>();
			List<Integer> listB = new ArrayList<Integer>();

			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					listA.add(i);
				} else
					listB.add(i);
			}

			if (listA.size() == 0 || listB.size() == 0)
				return;
			
			if(check(listA)&&check(listB)) {
				getPeopleDiff();
			}
			
			return;
		}

		isSelected[depth] = true;
		divide(depth + 1);
		isSelected[depth] = false;
		divide(depth + 1);
	}
	
	public static boolean check(List<Integer> list) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited = new boolean[N];
		visited[list.get(0)] = true;
		q.offer(list.get(0));
		int count = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<abjList.get(cur).size(); i++) {
				int nextNode = abjList.get(cur).get(i);
				if(list.contains(nextNode)&&!visited[nextNode]) {
					q.offer(nextNode);
					visited[nextNode] = true;
					count++;
				}
			}
		}
		
		if(count==list.size()) return true;
		return false;
	}
	
	public static void getPeopleDiff() {
		int pA = 0, pB = 0;
		for(int i=0; i<N; i++) {
			if(isSelected[i]) pA += peoples[i];
			else pB+=peoples[i];
		}
		
		int diff = Math.abs(pA-pB);
		min = Math.min(min, diff);
	}
}
