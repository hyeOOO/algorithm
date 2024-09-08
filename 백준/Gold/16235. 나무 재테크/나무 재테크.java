import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age; // 나이가 어린 나무가 먼저 처리되도록
		}
	}

	static int N, M, K;
	static int[][] A;
	static int[][] eat;
	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dx = { -1, 0, 1, 1, -1, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		eat = new int[N][N];
		List<Tree> treeList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				eat[i][j] = 5; // 초기 양분은 5로 설정
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int year = Integer.parseInt(st.nextToken());

			treeList.add(new Tree(x, y, year));
		}

		while (K-- > 0) {
			List<Tree> deadTrees = new ArrayList<>();
			List<Tree> newTrees = new ArrayList<>();

			// 봄 - 나무가 양분을 먹고 자람
			Collections.sort(treeList); // 나이가 어린 순으로 정렬
			List<Tree> survivedTrees = new ArrayList<>();

			for (Tree tree : treeList) {
				if (eat[tree.x][tree.y] >= tree.age) {
					eat[tree.x][tree.y] -= tree.age; // 양분을 먹고
					tree.age++; // 나이 1 증가
					survivedTrees.add(tree); // 살아남은 나무 목록에 추가
				} else {
					deadTrees.add(tree); // 죽은 나무 목록에 추가
				}
			}

			// 여름 - 죽은 나무가 양분이 됨
			for (Tree tree : deadTrees) {
				eat[tree.x][tree.y] += tree.age / 2;
			}

			// 가을 - 나이가 5의 배수인 나무가 번식
			for (Tree tree : survivedTrees) {
				if (tree.age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						int nx = tree.x + dx[d];
						int ny = tree.y + dy[d];

						if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
							newTrees.add(new Tree(nx, ny, 1)); // 나이가 1인 나무 생성
						}
					}
				}
			}
			
			// 나무 번식 후 리스트에 추가
            treeList = new ArrayList<>(survivedTrees);
            treeList.addAll(0, newTrees); // 나이가 어린 나무가 앞에 오도록 추가

            // 겨울 - 양분 추가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    eat[i][j] += A[i][j];
                }
            }

		}

		 // 최종적으로 살아남은 나무 수 출력
        System.out.println(treeList.size());
	}
}
