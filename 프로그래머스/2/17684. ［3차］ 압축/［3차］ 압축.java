import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dic = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<26; i++){
            char c = (char)('A'+i);
            dic.put(""+c, i+1);
        }
        
        int idx=0;
        int dicNum = 27;
        
        while(idx<msg.length()){
            String nextWord = ""+msg.charAt(idx);
            
            // 현재 문자열 확장
            while(idx+1<msg.length() && dic.containsKey(nextWord+msg.charAt(idx+1))){
                idx++;
                nextWord += msg.charAt(idx);
            }
            
            // 출력
            answer.add(dic.get(nextWord));
            
            // 다음 문자열 존재 시 사전에 추가
            if(idx+1 < msg.length()){
                String newWord = nextWord+msg.charAt(idx+1);
                dic.put(newWord, dicNum++);
            }
            // 다음 단어 체크
            idx++;
        }
        
        // list -> array
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}