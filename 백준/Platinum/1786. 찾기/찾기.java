import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int EXPONENT1 = 31;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        List<Integer> answers = new ArrayList<>();

        String str = br.readLine();
        String pattern = br.readLine();

        int strSize = str.length();
        int patternSize= pattern.length();

        if(strSize<patternSize) {
            System.out.println(0);
            return;
        }

        long strHash1 = 0;
        long patternHash1 = 0;
        long power1 = 1;

        for(int i=0; i<=strSize-patternSize; i++){
            if(i==0){
                for(int j=0; j<patternSize; j++){
                    strHash1 = (strHash1 + str.charAt(patternSize-1-j) * power1) % MOD;
                    patternHash1 = (patternHash1 + pattern.charAt(patternSize-1-j) * power1) % MOD;

                    if(j < patternSize - 1){
                        power1 = (power1 * EXPONENT1) % MOD;  // 수정: 모듈로 연산 추가
                    }
                }
            }else{
                strHash1 = (EXPONENT1 * (strHash1 - str.charAt(i-1) * power1) + str.charAt(patternSize-1+i)) % MOD;
                if(strHash1 < 0) strHash1 += MOD;  // 음수 처리
            }

            if(strHash1 == patternHash1){
                count++;
                answers.add(i+1);
            }
        }

        System.out.println(count);
        for(int answer : answers){
            System.out.print(answer + " ");
        }
    }
}
