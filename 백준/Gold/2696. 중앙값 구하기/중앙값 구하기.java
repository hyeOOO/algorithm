import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t=1; t<=tc; t++){
            int M = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> maxH = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minH = new PriorityQueue<>();

            int[] arr = new int[M];
            int idx= 0;
            int idx2 = 0;
            List<Integer> answer = new ArrayList<>();
            int input = M/10;
            input += M%10>0?1:0;
            for(int i=0; i<input; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                while(st.hasMoreElements()){
                    arr[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            StringBuilder sb = new StringBuilder();

            for(int i=0; i<M; i++){
                int n = arr[i];
                if(i%2==0)maxH.add(n);
                else minH.add(n);

                while(!minH.isEmpty()&&!maxH.isEmpty()&&maxH.peek() > minH.peek()){
                    int min = minH.remove();
                    int max = maxH.remove();

                    minH.add(max);
                    maxH.add(min);
                }

                if(i%2==0) {
                    answer.add(maxH.peek());
                }
            }

            sb.append(answer.size());
            sb.append("\n");

            for(int i=0; i<answer.size(); i++){
                sb.append(answer.get(i));
                sb.append(" ");
                if((i+1)%10==0)sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }
}
