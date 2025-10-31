import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> sub = new Stack<>();
        int answer = 0;
        int boxNum = 1; // 현재 컨베이어 벨트에서 나올 박스 번호
        
        for(int want : order) {
            // 컨베이어 벨트에서 원하는 박스가 나올 때까지 보조 컨베이어에 적재
            while(boxNum <= order.length && boxNum < want) {
                sub.push(boxNum);
                boxNum++;
            }
            
            // 메인 컨베이어에서 바로 실을 수 있는 경우
            if(boxNum == want) {
                answer++;
                boxNum++;
            }
            // 보조 컨베이어의 맨 위에서 실을 수 있는 경우
            else if(!sub.isEmpty() && sub.peek() == want) {
                sub.pop();
                answer++;
            }
            // 둘 다 안되면 더 이상 진행 불가
            else {
                break;
            }
        }
        
        return answer;
    }
}