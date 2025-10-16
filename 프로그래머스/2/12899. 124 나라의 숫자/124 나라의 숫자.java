class Solution {
    public String solution(int n) {
        if(n < 3) return Integer.toString(n);
        
        StringBuilder result = new StringBuilder();
        
        while(n > 0) {
            int rem = n % 3;
            n = (n-1) / 3;
            
            if(rem == 0) rem = 4;
            result.insert(0, rem);
        }
        
        return result.toString();
    }
}