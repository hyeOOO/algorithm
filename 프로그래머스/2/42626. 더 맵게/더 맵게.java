import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int sco : scoville)
            pq.offer(sco);
        
        while(true){
            // 제일 낮은 음식의 스코빌 지수가 K 이상일 경우 탈출
            if(pq.peek()>=K) break;
            
            // 음식 수가 2개 이하인 경우 -1
            if(pq.size()<2){
                answer = -1;
                break;
            }
            
            int first = pq.poll();
            int second = pq.poll();
            
            // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 -1
            if(first==0 && second==0){
                answer = -1;
                break;
            }
            
            pq.offer(first+second*2);
            answer++;
        }
        
        return answer;
    }
}