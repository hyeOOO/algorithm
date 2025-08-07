class Solution {
    public int solution(int[] money) {
        int len = money.length;
        
        // 첫번째 집 털고 마지막 집 안털기
        int case1 = stealing(money, 0, len-2);
        // 첫번째 집 안털고 마지막 집 털기
        int case2 = stealing(money, 1, len-1);
        
        return Math.max(case1, case2);
    }
    
    public static int stealing(int[] money, int start, int end){
        int prev2 = 0;
        int prev1 = 0;
        
        for(int i=start; i<=end; i++){
            int cur = Math.max(prev1, prev2+money[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        
        return prev1;
    }
}