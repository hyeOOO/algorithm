import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        ArrayList<TreeSet<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new TreeSet<>());
        }
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                int k = Integer.parseInt(st.nextToken());
                list.get(i).add(k);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(Q-->0) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            
            if(c == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if(list.get(a).size() < list.get(b).size()) {
                    list.get(b).addAll(list.get(a));
                    list.get(a).clear();
                    Collections.swap(list, a, b);
                } else {
                    list.get(a).addAll(list.get(b));
                    list.get(b).clear();
                }
            } else {
                int a = Integer.parseInt(st.nextToken());
                sb.append(list.get(a).size()).append('\n');
            }
        }
        System.out.print(sb);
    }
}