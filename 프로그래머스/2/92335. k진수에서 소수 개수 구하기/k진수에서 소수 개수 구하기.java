import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String binaryNumber = Integer.toString(n, k); // k진수로 변환
        String[] parts = binaryNumber.split("0");
        
        for(String part : parts){
            if(part.isEmpty()) continue;
            
            long num = Long.parseLong(part);
            if(isPrime(num)) answer++;
        }
        return answer;
    }
    
    public boolean isPrime(long n){
        if(n<2) return false;
        // n이 약수를 가질 때, 약수 쌍 중 하나는 루트 n 이하에 존재
        for(long i=2; i<=Math.sqrt(n); i++){
            if(n%i==0) return false;
        }
        return true;
    }
}