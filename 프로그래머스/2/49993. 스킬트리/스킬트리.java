import java.util.*;

class Solution {
    // 스킬의 차수를 저장한 배열
    static int[] skillStep = new int[26];
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skillTree : skill_trees){
            Arrays.fill(skillStep, 0);
            for(int i=0; i<skill.length(); i++){
                skillStep[skill.charAt(i)-'A'] = i+1;
            }
            
            boolean canLearn = true;
            for(char s : skillTree.toCharArray()){
                if(skillStep[s-'A']<1) continue;
                else if(skillStep[s-'A']==1){
                    for(int i=0; i<26; i++){
                        if(skillStep[i]>1) skillStep[i]--;
                    }
                }else{
                    canLearn = false;
                    break;
                }
            }
            if(canLearn) answer++;
        }
        return answer;
    }
}