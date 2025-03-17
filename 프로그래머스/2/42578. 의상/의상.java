import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        // (옷 종류, 갯수) 식으로 맵 초기화
        for(String[] clothe : clothes){
            if(!map.containsKey(clothe[1])){
                map.put(clothe[1], 1);
                continue;
            }
            map.put(clothe[1], map.get(clothe[1])+1);
        }
        
        // 아이템 종류 + 1(해당 종류의 의상을 입지 않는 경우) 곱셈
        int total = 1;
        for(String key : map.keySet()){
            total *= (map.get(key) + 1);
        }
        
        // 모든 종류의 의상을 입지 않는 경우 제외
        return total - 1;
        
    }
}