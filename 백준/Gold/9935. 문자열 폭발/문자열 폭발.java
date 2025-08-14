import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            // StringBuilder의 길이가 폭발 문자열보다 크거나 같으면 체크
            if (sb.length() >= target.length()) {
                boolean isMatch = true;

                // StringBuilder의 마지막 target.length() 개 문자가 폭발 문자열과 같은지 확인
                for (int j = 0; j < target.length(); j++) {
                    if (sb.charAt(sb.length() - target.length() + j) != target.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }

                // 일치하면 해당 문자들을 StringBuilder에서 제거
                if (isMatch) {
                    sb.setLength(sb.length() - target.length());
                }
            }
        }

        // 결과 출력
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }

    }
}
