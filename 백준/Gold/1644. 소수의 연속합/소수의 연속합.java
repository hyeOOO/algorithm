import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static boolean[] isPrime;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        makePrime();

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        while(start<=end && end<list.size()){
            if(sum<N){
                sum+=list.get(end++);
            }else{
                if(sum==N) count++;
                sum -= list.get(start++);
            }
        }

        System.out.println(count);
    }

    public static void makePrime(){
        isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for(int i=2; i*i<=N; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=N; j+=i){
                    isPrime[j] = false;
                }
            }
        }

        for(int i=1; i<=N; i++){
            if(isPrime[i]){
                list.add(i);
            }
        }

        list.add(0);
    }
}
