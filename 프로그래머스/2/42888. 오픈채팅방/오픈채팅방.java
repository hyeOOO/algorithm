import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        List<String[]> commands = new ArrayList<>();
        
        for(String r : record){
            StringTokenizer st = new StringTokenizer(r);
            String cmd = st.nextToken();
            String userId = st.nextToken();
            
            if(cmd.equals("Enter")){
                String nickName = st.nextToken();
                userMap.put(userId, nickName);
                commands.add(new String[]{cmd, userId});
            }else if(cmd.equals("Change")){
                String nickName = st.nextToken();
                userMap.put(userId, nickName);
            }else{
                commands.add(new String[]{cmd, userId});
            }
        }
        
        String[] answer = new String[commands.size()];
        for(int i=0; i<commands.size(); i++){
            String userId = commands.get(i)[1];
            if(commands.get(i)[0].equals("Enter")){
                answer[i] = userMap.get(userId)+"님이 들어왔습니다.";
            }else{
                answer[i] = userMap.get(userId)+"님이 나갔습니다.";
            }
        }
        return answer;
    }
}