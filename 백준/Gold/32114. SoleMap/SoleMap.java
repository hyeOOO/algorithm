
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] firstLine = br.readLine().split(" ");
		int N = Integer.parseInt(firstLine[0]); // 도시수
		int M = Integer.parseInt(firstLine[1]); // 이동정보수

		//각 도로 차로 수 입력
		String[] laneInput = br.readLine().split(" ");
		int[] lanes = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			lanes[i] = Integer.parseInt(laneInput[i]);
		}

		//교통량 정보 누적합
		long[] traffic = new long[N];

		for (int i = 0; i < M; i++) {
			String[] trafficInput = br.readLine().split(" ");
			int u = Integer.parseInt(trafficInput[0]) - 1;
			int v = Integer.parseInt(trafficInput[1]) - 1;
			int x = Integer.parseInt(trafficInput[2]);

			//누적합 계산
			traffic[u] += x;
			traffic[v] -= x;
		}

		//실제 도로의 교통량 계산
		long[] actualTraffic = new long[N - 1];
		long currentTraffic = 0;
		for (int i = 0; i < N - 1; i++) {
			currentTraffic += traffic[i];
			actualTraffic[i] = currentTraffic;
		}

		//도로 부담 계산
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N - 1; i++) {
			long c = actualTraffic[i];
			int w = lanes[i];

			long base = c / w; //기본차량수 처리
			long remain = c % w; //나누어 떨어지지않는 나머지 차량 수

			long answer = remain * (base + 1) * (base + 1) + (w - remain) * base * base;
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}