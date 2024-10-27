import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Beer implements Comparable<Beer> {
    int preference;
    int alcohol;

    public Beer(int preference, int alcohol) {
        this.preference = preference;
        this.alcohol = alcohol;
    }

    @Override
    public int compareTo(Beer other) {
        return this.alcohol - other.alcohol; // 도수를 기준으로 오름차순 정렬
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 축제에서 마셔야 하는 맥주 종류 수
        int m = Integer.parseInt(st.nextToken()); // 최소 선호도 합
        int k = Integer.parseInt(st.nextToken()); // 맥주 종류 수

        Beer[] beers = new Beer[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int preference = Integer.parseInt(st.nextToken());
            int alcohol = Integer.parseInt(st.nextToken());
            beers[i] = new Beer(preference, alcohol);
        }

        // 도수 기준으로 오름차순 정렬
        Arrays.sort(beers);

        PriorityQueue<Integer> preferenceQueue = new PriorityQueue<>();
        long preferenceSum = 0;
        int minAlcohol = -1;

        for (Beer beer : beers) {
            preferenceQueue.add(beer.preference);
            preferenceSum += beer.preference;

            if (preferenceQueue.size() > n) {
                preferenceSum -= preferenceQueue.poll(); // 가장 작은 선호도를 제거하여 개수 유지
            }

            if (preferenceQueue.size() == n && preferenceSum >= m) {
                minAlcohol = beer.alcohol; // 조건을 만족하는 최소 도수
                break;
            }
        }

        System.out.println(minAlcohol);
    }
}
