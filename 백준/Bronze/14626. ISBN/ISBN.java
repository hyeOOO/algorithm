import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        String str = br.readLine();
        
        int check = 0, num = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch != '*') {
                num += i % 2 == 0 ? ch - '0' : (ch - '0') * 3;
            }
            else {
                check = i;
            }
        }
        
        int answer = 0;
        while(true) {
            if(check % 2 == 0) {
                if((num + answer) % 10 == 0) {
                    System.out.print(answer);
                    break;
                }
            }
            else {
                if((num + answer * 3) % 10 == 0) {
                    System.out.print(answer);
                    break;
                }
            }
            answer++;
        }
    }
}