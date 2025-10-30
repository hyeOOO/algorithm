import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int result = 0;
        
        // 오른쪽에 있는 토핑 종류 개수 세기
        Map<Integer, Integer> rightCount = new HashMap<>();
        for (int t : topping) {
            rightCount.put(t, rightCount.getOrDefault(t, 0) + 1);
        }
        
        // 왼쪽 토핑 종류
        Set<Integer> leftSet = new HashSet<>();
        
        // 각 분할점 확인
        for (int i = 0; i < topping.length - 1; i++) {
            // 왼쪽에 현재 토핑 추가
            leftSet.add(topping[i]);
            
            // 오른쪽에서 현재 토핑 제거
            rightCount.put(topping[i], rightCount.get(topping[i]) - 1);
            if (rightCount.get(topping[i]) == 0) {
                rightCount.remove(topping[i]);
            }
            
            // 종류 개수가 같으면 카운트
            if (leftSet.size() == rightCount.size()) {
                result++;
            }
        }
        
        return result;
    }
}