//시간초과뜸 ㅋ 걍 Arrays.sort 썼는데 카운팅 정렬에 대해 알아야할듯함.
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2108 {
	public static int getAvg(int[] arr) {
		double total = 0;
		for (int a : arr) {
			total += a;
		}
		return (int) Math.round(total / arr.length);
	}

	public static int getCenter(int[] sortArr) {
		if (sortArr.length < 2)
			return sortArr[0];
		int center = (sortArr.length - 1) / 2; 
		return sortArr[center];
	}

	public static int getFreq(int[] arr) {
		if(arr.length<2) return arr[0];
		int[][] indexArr = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			for (int j = i; j < arr.length; j++) {
				if (arr[i] == arr[j])
					count++;
			}
			indexArr[i][0] = arr[i];
			indexArr[i][1] = count;
		}

		Arrays.sort(indexArr, (o1, o2) -> {
			return o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1];
		});
		
		if(indexArr[0][1]==indexArr[1][1]) return indexArr[1][0];
		else return indexArr[0][0];
	}

	public static int getRange(int[] sortArr) {
		if (sortArr.length == 1)
			return 0;
		return sortArr[sortArr.length - 1] - sortArr[0];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[] sortArr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(sortArr);

		sb.append(getAvg(arr)).append("\n");
		sb.append(getCenter(sortArr)).append("\n");
		sb.append(getFreq(arr)).append("\n");
		sb.append(getRange(sortArr)).append("\n");

		System.out.println(sb);
	}
}
