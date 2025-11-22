class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];
        
        for (long i = begin; i <= end; i++) {
            answer[(int)(i - begin)] = getMaxDivisor(i);
        }
        
        return answer;
    }
    
    // 최대 약수를 직접 구하기 (최적화 버전)
    public static int getMaxDivisor(long n) {
        if (n == 1) return 0;
        
        int maxDiv = 1;  // 기본값은 1
        
        // √n까지만 확인하면서 약수 쌍을 찾기
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // i는 작은 약수, n/i는 큰 약수
                long largerDiv = n / i;
                
                // 블록 번호는 10,000,000 이하여야 함
                if (largerDiv <= 10_000_000) {
                    return (int)largerDiv;  // 가장 큰 약수 발견!
                }
                
                // 작은 약수 중 최대값 갱신
                maxDiv = (int)i;
            }
        }
        
        return maxDiv;
    }
}