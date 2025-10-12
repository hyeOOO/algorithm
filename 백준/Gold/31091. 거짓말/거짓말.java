import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            int n = Integer.parseInt(st.nextToken());
            if(n<=0) neg.add(n);
            else pos.add(n);
        }

        Collections.sort(pos);
        Collections.sort(neg);

        int answer = 0;
        StringBuilder sb = new StringBuilder();

        for(int lier=0; lier<=N; lier++){
            int realLier = 0;
            realLier += (neg.size()-binarySearch(neg, -1*lier));
            realLier += (pos.size()-binarySearch(pos, lier));

            if(lier == realLier){
                answer++;
                sb.append(lier).append(' ');
            }
        }

        System.out.println(answer);
        System.out.println(sb);
    }

    public static int binarySearch(List<Integer> list, int target){
        int left = 0;
        int right = list.size()-1;
        int result = list.size();

        while(left<=right){
            int mid = (left+right)/2;

            if(target<list.get(mid)){ // 중간 인덱스의 value 값이 target 값보다 크다면, mid 줄이기
                right = mid-1;
                result = mid;
            }else{
                left = mid+1;
            }
        }

        return result;
    }
}
