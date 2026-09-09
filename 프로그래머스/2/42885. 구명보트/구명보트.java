import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int lightIdx = 0;
        int heavyIdx = people.length-1;
        
        while(lightIdx<=heavyIdx){
             if(people[lightIdx]+people[heavyIdx]<=limit){
                 lightIdx++;
             }
            heavyIdx--;
            answer++;
        }
        return answer;
    }
}