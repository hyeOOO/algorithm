import java.util.*;

class Solution {
    static int N;
    static List<Integer> answer;
    static boolean[] visited;
    
    public int solution(int[] cards) {
        answer = new ArrayList<>();
        N = cards.length;
        visited = new boolean[N+1];
        
        for(int i=1; i<=N; i++){
            if(!visited[i]){
                dfs(i, 1, cards);
            }
        }
        
        Collections.sort(answer);
        
        // 그룹이 1개 이하면 0 반환
        if(answer.size() < 2){
            return 0;
        }
        
        int result = answer.get(answer.size()-1)*answer.get(answer.size()-2);
        
        return result;
    }
    
    public static void dfs(int s, int count, int[] cards){     
        visited[s] = true;
        
        int next = cards[s-1];
        
        if(!visited[next]){
            dfs(next, count+1, cards);
        }else{
            answer.add(count);
            return;
        }
    }
}