import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        int result = 0;
        
        while(left<=right){
            int mid = (left+right)/2;
            int count = 0;
            int prev = 0;
            
            for(int i=0; i<rocks.length; i++){
                if(rocks[i]-prev<mid){
                    count++;
                    continue;
                }
                prev = rocks[i];
            }
            
            // 끝지점 카운트
            if(distance-prev<mid){
                count++;
            }
            
            if(count<=n){
                result = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return result;
    }
}