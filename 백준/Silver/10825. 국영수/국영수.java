import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student> {
		public String name;
		public int kor;
		public int eng;
		public int mat;

		public Student(String name, int kor, int eng, int mat) {
			super();
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
		}

		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
//			국어 점수가 감소하는 순서로
//			국어 점수가 같으면 영어 점수가 증가하는 순서로
//			국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
//			모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)

			if (this.kor != o.kor) {
				return Integer.compare(o.kor, this.kor);
			} else {
				if (this.eng != o.eng) {
					return Integer.compare(this.eng, o.eng);
				} else {
					if (this.mat != o.mat) {
						return Integer.compare(o.mat, this.mat);
					} else {
						return this.name.compareTo(o.name);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Student> students = new ArrayList<>();

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int mat = Integer.parseInt(st.nextToken());

			Student student = new Student(name, kor, eng, mat);
			students.add(student);
		}

		Collections.sort(students);
		
		for(Student s : students) {
			System.out.println(s.name);
		}
	}
}
