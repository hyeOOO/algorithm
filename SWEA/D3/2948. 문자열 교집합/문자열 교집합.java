import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t=1; t<=tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            HashSet<String> s = new HashSet<>();
            int answer =0 ;
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++){
                s.add(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                if(s.contains(st.nextToken())) answer++;
            }
            System.out.printf("#%d %d\n",t, answer);
        }
	}
}