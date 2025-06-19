import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[] alpha = new String[]{"c=","c-","dz=","d-","lj","nj","s=","z="};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		String input = new String(origin);
		int count = 0;
		
		for(int i=0; i<alpha.length; i++) {
			while(input.contains(alpha[i])) {
                input = input.replaceFirst(alpha[i], "X"); // X로 대체
                count++;
            }
		}
		
		// 남은 일반 문자들 개수 세기
        for(char c : input.toCharArray()) {
            if(c != 'X') {
                count++;
            }
        }
		
        System.out.println(count);
	}
}
