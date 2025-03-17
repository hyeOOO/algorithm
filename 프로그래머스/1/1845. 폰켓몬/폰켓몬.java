import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int size = nums.length/2;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        // 해시맵 초기화
        for(int n : nums){
            if(!map.containsKey(n)){
                map.put(n, 1);
                continue;
            }
            
            map.put(n, map.get(n)+1);
        }
        
        if(map.size()>=size) return size;
        return map.size();
    }
}