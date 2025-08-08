import java.util.*;

// Dp 테이블 정의: N을 i 번썼을때 표현할 수 있는 수 집합

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<=8; i++){
            list.add(new HashSet<>());
        }
        
        for(int i=1; i<=8; i++){
            int repeated = Integer.parseInt(String.valueOf(N).repeat(i)); //5를 i번 이어붙이기
            list.get(i).add(repeated);
            
            for(int j=1; j<i; j++){ 
                for(int a : list.get(j)){
                    for(int b : list.get(i-j)){
                        list.get(i).add(a+b);
                        list.get(i).add(a*b);
                        list.get(i).add(a-b);
                        if(b!=0) list.get(i).add(a/b);
                    }
                }
            }
            
            if(list.get(i).contains(number)){
                return i;
            }
        }
        
        return -1;
    }
}