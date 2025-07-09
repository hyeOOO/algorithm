import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            long answer = solution(A, B, K);
            System.out.printf("#%d %d\n", tc, answer);
        }
    }

    public static long solution(int n1, int n2, int count){
        int total = n1+n2;
        int max = total/2;
        long result = ((long)get2Power(total, count)*(long)n1)%total;
        // min 값을 반환해야하니까
        return result>max?total-result:result;
    }

    public static int get2Power(int total, int count){
        long res = 1;
        long num = 2;
        while(count>0){
            if(count%2==1){ // 지수가 홀수면 2를 한 번 더 곱해줘야함
                res = (res * num) % total;
            }
            num = (num*num) % total;
            count /= 2;
        }

        return (int)res;
    }
}