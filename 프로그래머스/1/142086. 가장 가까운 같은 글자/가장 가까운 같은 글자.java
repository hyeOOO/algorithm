import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Arrays.fill(answer,-1);
        for(int i=0; i<s.length(); i++){
            for(int j=i+1; j<s.length(); j++){
                if(s.charAt(j)==s.charAt(i)){
                    answer[j] = j-i;
                }
            }
        }
        
        return answer;
    }
}