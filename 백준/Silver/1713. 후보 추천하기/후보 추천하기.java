import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//사진틀은 추천받은 후보 수 만큼 만듬
// 추천받은 학생의 사진을 사진틀에 게시하고 추천받은 횟수를 표시하는 규칙

// 추천 시작 전에는 모든 사진틀이 비어있음
// 어떤 학생이 특정 학생을 추천 시, 추천 학생은 후보가 되어 사진틀에 반드시 게시
// 비어있는 사진틀이 없을 경우에는 현재까지 추천받은 횟수가 가장 적은 학생의 사진을 삭제, 그 자리에 새롭게 추천 받은 학생의 사진을 게시
// 추천 받은 횟수가 가장 적은 학생이 2명 이상이면 그 학생 중 가장 오래된 사진을 삭제
// 현재 사진이 게시된 학생 중 다른 학생의 추천을 받으면 추천수만 증가
// 사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생의 추천 수는 0으로 초기화
// 최종 후보 리스트 출력
public class Main {
	static int N, M;
	static List<Student> result;

	static class Student implements Comparable<Student> {
		int no;
		int recommendCount;
		int recommendDate;

		public Student(int no, int recommendCount, int recommendDate) {
			super();
			this.no = no;
			this.recommendCount = recommendCount;
			this.recommendDate = recommendDate;
		}

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public int getRecommendCount() {
			return recommendCount;
		}

		public void setRecommendCount(int recommendCount) {
			this.recommendCount = recommendCount;
		}

		public int getRecommendDate() {
			return recommendDate;
		}

		public void setRecommendDate(int recommendDate) {
			this.recommendDate = recommendDate;
		}

		public int compareTo(Student s1) {
			// 추천순 > 추천받은 순
			if(s1.recommendCount!=this.recommendCount) {
				return Integer.compare(this.recommendCount, s1.recommendCount);
			}else {
				return Integer.compare(this.recommendDate, s1.recommendDate);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		result = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			// 추천 받은 학생들이 이미 이전에 추천 받은 전적이 있는지 검사
			boolean flag = false;
			int recommendStudent = Integer.parseInt(st.nextToken());
			for (int j = 0; j < result.size(); j++) {
				// 전적이 있다면 추천 수만 증가
				if (recommendStudent == result.get(j).getNo()) {
					Student s = result.get(j);
					s.setRecommendCount(s.getRecommendCount() + 1);
					flag = true;
					break;
				}
			}

			// 추천받은 적 없으면 객체 만들어서 리스트에 추가
			if (!flag) {
				// 사진 틀의 자리가 있으면 그냥 추가
				if (result.size() < N) {
					result.add(new Student(recommendStudent, 1, i));
				}else {
					// 자리 없으면 제일 마지막 요소 제거하고 추가(추천수가 같으면 오래된 거 삭제)
					Collections.sort(result);
					result.remove(0);
					result.add(new Student(recommendStudent,1,i));
				}
			}
			
			 Collections.sort(result);
		}

		Collections.sort(result, (o1,o2)->o1.no-o2.no);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<result.size(); i++) {
			sb.append(result.get(i).getNo()+" ");
		}
		System.out.println(sb.toString());
	}
}
