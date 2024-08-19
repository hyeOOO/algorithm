package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class User implements Comparable<User> {
	private int age;
	private String name;
	private int index;

	User(int age, String name, int index) {
		this.age = age;
		this.name = name;
		this.index = index;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public int compareTo(User o) {
		if (getAge() != o.getAge())
			return Integer.compare(getAge(), o.getAge());
		else
			return Integer.compare(getIndex(), o.getIndex());

	}
}

public class Baekjoon10814 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		List<User> users = new ArrayList<>();
		int index = 0;

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input, " ");

			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			users.add(new User(age, name, index++));
		}

		Collections.sort(users);

		for (User u : users) {
			System.out.println(u.getAge() + " " + u.getName());
		}
	}
}
