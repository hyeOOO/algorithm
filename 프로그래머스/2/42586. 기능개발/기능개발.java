import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        Queue<int[]> q = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<speeds.length; i++){
            q.offer(new int[]{progresses[i], speeds[i]});
        }
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int count = 1;
            int needDays = (100-cur[0])/cur[1];
            needDays += ((100-cur[0])%cur[1]>0?1:0);
            
            while(!q.isEmpty()){
                int[] next = q.peek();
                
                int nextNeedDays = (100-next[0])/next[1];
                nextNeedDays += ((100-next[0])%next[1]>0?1:0);
                
                if(needDays>=nextNeedDays){
                    count++;
                    q.poll();
                }else{
                    break;
                }
            }
            
            answer.add(count);
        }
        
        return answer;
    }
}