import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long H = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken())+1;
        long M = Long.parseLong(st.nextToken())+1;
        long result = H/N;
        result += H%N!=0?1:0;
        long result2 = W/M;
        result2 += W%M!=0?1:0;

        System.out.println(result*result2);

    }
}
