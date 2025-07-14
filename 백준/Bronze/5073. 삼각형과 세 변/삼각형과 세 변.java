import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] triangle = new int[3];

            for (int i = 0; i < 3; i++) {
                triangle[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(triangle);

            // 종료 조건: 0 0 0 입력시 종료
            if(triangle[0] == 0 && triangle[1] == 0 && triangle[2] == 0) break;

            if (triangle[0] == triangle[2]) System.out.println("Equilateral");
            else if (triangle[2] >= triangle[0] + triangle[1]) System.out.println("Invalid");
            else if (triangle[0] == triangle[1] || triangle[1] == triangle[2]) {
                System.out.println("Isosceles");
            } else System.out.println("Scalene");
        }

    }
}
