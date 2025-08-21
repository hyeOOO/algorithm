import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
    static int n, k;
    static int[][] aquarium;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
 
    public static void main(String[] args) throws IOException {
        initialize();
 
        int count = 0;
        while (maxDifferenceExceeds(k)) {
            count++;
            increaseFish();
            firstFold();
            spreadAlgae();
            unfold();
            secondFold();
            spreadAlgae();
            unfold();
        }
 
        System.out.println(count);
    }
 
    public static boolean maxDifferenceExceeds(int k) {
        int minFish = Integer.MAX_VALUE;
        int maxFish = Integer.MIN_VALUE;
 
        for (int i = 0; i < n; i++) {
            minFish = Math.min(minFish, aquarium[i][0]);
            maxFish = Math.max(maxFish, aquarium[i][0]);
        }
 
        return maxFish - minFish > k;
    }
 
    public static void increaseFish() {
        int minFish = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            minFish = Math.min(minFish, aquarium[i][0]);
 
        for (int i = 0; i < n; i++) {
            if (aquarium[i][0] == minFish)
                aquarium[i][0]++;
        }
    }
 
    public static void firstFold() {
        int startX = 0;
        int vertical = 1;
        int horizontal = 1;
        while (startX + vertical + horizontal <= n) {
            for (int i = vertical - 1; i >= 0; i--) {
                for (int j = 0; j < horizontal; j++) {
                    int newX = startX + vertical + j;
                    int newY = vertical - i;
                    aquarium[newX][newY] = aquarium[startX + i][j];
                    aquarium[startX + i][j] = 0;
                }
            }
            startX += vertical;
            if (vertical == horizontal) horizontal++;
            else vertical++;
        }
    }
 
    public static void spreadAlgae() {
        int[][] save = new int[n][25];
        boolean[][] visited = new boolean[n][25];
 
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < 25; y++) {
                visited[x][y] = true;
                if (aquarium[x][y] == 0) continue;
 
                for (int dir = 0; dir < 4; dir++) {
                    int newX = x + dx[dir];
                    int newY = y + dy[dir];
                    if (isValid(newX, newY) && !visited[newX][newY] && aquarium[newX][newY] != 0) {
                        int diff = (aquarium[x][y] - aquarium[newX][newY]) / 5;
                        if (Math.abs(diff) >= 1) {
                            save[x][y] -= diff;
                            save[newX][newY] += diff;
                        }
                    }
                }
            }
        }
 
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < 25; y++) {
                aquarium[x][y] += save[x][y];
            }
        }
    }
 
    public static void unfold() {
        int x = 0;
        while (aquarium[x][0] == 0) x++;
 
        int index = 0;
        int[] save = new int[n];
        for (int i = x; i < n; i++) {
            for (int j = 0; j < 25; j++) {
                if (aquarium[i][j] == 0) break;
                save[index++] = aquarium[i][j];
                aquarium[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            aquarium[i][0] = save[i];
        }
    }
 
    public static void secondFold() {
        for (int i = 0; i < n / 2; i++) {
            aquarium[n - 1 - i][1] = aquarium[i][0];
            aquarium[i][0] = 0;
        }
 
        for (int i = 0; i < n / 4; i++) {
            for (int j = 0; j < 2; j++) {
                aquarium[n - 1 - i][3 - j] = aquarium[n / 2 + i][j];
                aquarium[n / 2 + i][j] = 0;
            }
        }
    }
 
    static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        k = Integer.parseInt(line[1]);
        aquarium = new int[n][25];
 
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            aquarium[i][0] = Integer.parseInt(line[i]);
        }
    }
 
    public static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < 25;
    }
}