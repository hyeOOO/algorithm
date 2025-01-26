import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // MOBIS 조건 생성
        Set<Character> mobis = Set.of('M', 'O', 'B', 'I', 'S');

        // 입력값 처리
        char[] input = br.readLine().toCharArray();
        Set<Character> str = new HashSet<>();
        for (char c : input) {
            str.add(c);
        }

        // 결과 출력
        if (str.containsAll(mobis)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
