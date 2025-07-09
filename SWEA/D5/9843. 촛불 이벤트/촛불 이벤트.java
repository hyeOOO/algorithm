import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            long N = Long.parseLong(br.readLine());
            long left = 1;
            long right = 10000000000L;
            long result = 0;

            while(left<=right){
                long mid = (left+right)/2;
                long value = mid * (mid+1)/2;

                if(N>=value){
                    result = mid;
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }

            long value = result * (result+1) / 2;
            if(N!=value) result = -1;

            System.out.printf("#%d %d\n",tc, result);
        }
    }
	
}