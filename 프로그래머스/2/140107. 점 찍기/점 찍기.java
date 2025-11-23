class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long dSquared = (long)d * d;
        
        for(long y = 0; y <= d; y += k) {
            long ySquared = y * y;
            long maxX = (long)Math.sqrt(dSquared - ySquared);
            
            // k의 배수인 x의 개수 계산
            answer += (maxX / k) + 1;
        }
        
        return answer;
    }
}