import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        PriorityQueue<Integer> mainBelt = new PriorityQueue<>();
        Stack<Integer> subBelt = new Stack<>();
        for(int o : order){
            mainBelt.offer(o);
        }
        
        int targetIdx = 0;
        while(true){
            // 모든 상자를 다 꺼낸 경우 중단 
            if(targetIdx == order.length) break;
            // mainBelt의 꼭대기와 subBelt 꼭대기 상자가 꺼내야하는 상자와 일치하는지 확인    
            if(!mainBelt.isEmpty() && mainBelt.peek() == order[targetIdx]){
                mainBelt.poll();
                answer++;
                targetIdx++;
                continue;
            }else if(!subBelt.isEmpty() && subBelt.peek() == order[targetIdx]){
                subBelt.pop();
                answer++;
                targetIdx++;
                continue;
            }else{
                // 일치하지 않았을 때, stack내 타깃 상자가 있다면 더 이상 못꺼냄
                if(!subBelt.isEmpty() && subBelt.contains(order[targetIdx])){
                    break;
                }
                // 일치하지 않았을 때, mainBelt내 타깃이 있다면 타깃 상자까지 다 빼야함
                if(!mainBelt.isEmpty() && mainBelt.contains(order[targetIdx])){
                    while(mainBelt.peek()!=order[targetIdx]){
                        subBelt.push(mainBelt.poll());
                    }
                }
            }
        }
        
        return answer;
    }
}