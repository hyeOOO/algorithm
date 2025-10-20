import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> nums = new ArrayList<>();
        k -= 1; // 0-based
        
        for(int i=1; i<=n; i++){
            nums.add(i);
        }
        
        for(int i=0; i<n; i++){
            long factorial = factorial(n-1-i);
            
            int idx = (int)(k/factorial);
            answer[i] = nums.remove(idx);
            
            k %= factorial;
        }
        return answer;
    }
    
    public long factorial(int n){
        long result = 1;
        for(int i=2; i<=n; i++){
            result *= i;
        }
        
        return result;
    }
}