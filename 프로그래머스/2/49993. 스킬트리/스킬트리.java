import java.util.*;

class Solution {
    // 스킬의 차수를 저장한 배열
    static int[] skillStep = new int[26];
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skillTree : skill_trees){
            Arrays.fill(skillStep, 0);
            // 주어진 스킬 순서에 따라 차수 증가
            for(int i = 0; i < skill.length(); i++){
                skillStep[skill.charAt(i) - 'A'] = i + 1;
            }
            
            boolean canLearn = true;
            for(char s : skillTree.toCharArray()){
                // 예외 스킬은 다 배울 수 있음
                if(skillStep[s - 'A'] < 1) continue;
                // 이제 배울 수 있는 스킬은 배우게 되면, 다음 스킬들의 차수 감소
                else if(skillStep[s - 'A'] == 1){
                    for(int i = 0; i < 26; i++){
                        if(skillStep[i] > 1) skillStep[i]--;
                    }
                }else{ // 스킬 차수가 0이나 1이 아닌데 배우려고 할 경우 해당 스킬트리는 x
                    canLearn = false;
                    break;
                }
            }
            if(canLearn) answer++;
        }
        return answer;
    }
}
