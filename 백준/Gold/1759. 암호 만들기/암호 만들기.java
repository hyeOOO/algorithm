import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호는 서로 다른 L개의 알파벳 소문자
// 최소 한 개의 모음(a,e,i,o,u) + 최소 두 개의 자음으로 구성
// 알파벳은 오름차순 정렬(ex. abc (o), bac(x))
public class Main {
	static int L,C;
	static char[] words;
	static char[] word;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		words = new char[C];
		word = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			words[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(words);
		makeCodes(0, 0);
		
	}
	
	public static void makeCodes(int idx, int count) {
		if(count==L) {
			if(isValid()) {
				System.out.println(word);
			}
			return;
		}
		
		for(int i=idx; i<C; i++) {
			word[count] = words[i];
			makeCodes(i+1, count+1);
		}
	}
	
	public static boolean isValid() {
		int mo = 0;
		int ja = 0;
		
		for(char c : word) {
			if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {
				mo++;
			}else {
				ja++;
			}
		}
		
		if(mo>=1&&ja>=2) return true;
		return false;
	}
}
