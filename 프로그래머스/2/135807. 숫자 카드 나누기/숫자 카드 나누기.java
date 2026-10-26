import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int[] cdA = getCommonDivisor(arrayA);
        
        for(int div : cdA){
            boolean divided = false; 
            for(int b : arrayB){
                if(b%div==0) {
                    divided = true;
                    break;
                }
            }
            if(!divided) answer = Math.max(answer, div);
        }
        
        int[] cdB = getCommonDivisor(arrayB);
        
        for(int div : cdB){
            boolean divided = false; 
            for(int a : arrayA){
                if(a%div==0) {
                    divided = true;
                    break;
                }
            }
            if(!divided) answer = Math.max(answer, div);
        }
        
        return answer;
    }
    
    // 공약수 리스트를 구하는 메서드
    public int[] getCommonDivisor(int[] arr){
        List<Integer> answer = new ArrayList<>();
        Arrays.sort(arr);
        
        for(int i=2; i<=arr[0]; i++){
            boolean divided = true;
            for(int a : arr){
                if(a%i!=0){
                    divided = false;
                    break;
                }
            }
            
            if(divided) answer.add(i);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}