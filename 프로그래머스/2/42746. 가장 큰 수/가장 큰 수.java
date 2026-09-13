import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        // 모든 수가 0이면 000 이 아니라 0 반환
        boolean isZero = true;
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] != 0) isZero = false;
            // int -> string으로 변환
            nums[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(nums, (o1, o2) -> {
            // 나열 순으로 더하는 게 큰 지, 바꿔서 더하는 게 큰 지 비교 후 정렬
            int origin = Integer.parseInt(o1 + o2);
            int reverse = Integer.parseInt(o2 + o1);
            return reverse - origin;
        });
        
        StringBuilder sb = new StringBuilder();
        
        if(!isZero){
            for(int i = 0; i < nums.length; i++){
                sb.append(nums[i]);
            }
        }else{
            sb.append("0");
        }
        
        return sb.toString();
    }
}
