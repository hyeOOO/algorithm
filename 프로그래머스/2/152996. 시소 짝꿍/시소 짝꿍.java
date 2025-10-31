import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> m = new HashMap<>();
        
        for(int w : weights){
            m.put(w, m.getOrDefault(w, 0)+1);
        }
        
        List<Integer> weightList = new ArrayList<>(m.keySet());
        
        for(int w : weightList){
            int count = m.get(w);
            if(count >= 2) {
                answer += (long)count * (count - 1) / 2;
            }
        }
        
        for(int i=0; i<weightList.size(); i++){
            for(int j=i+1; j<weightList.size(); j++){
                int a = weightList.get(i);
                int b = weightList.get(j);
                if(a<b && checkRatio(a,b)) {
                    answer += (long)m.get(a) * m.get(b);
                }else if(a>b && checkRatio(b,a)){
                    answer += (long)m.get(a) * m.get(b);
                }
            }
        }
        return answer;
    }
    
    public static boolean checkRatio(int a, int b){
        int g = gcd(a, b);
        int v1 = a/g;
        int v2 = b/g;
        
        if(v1==2&&v2==3) return true;
        if(v1==3&&v2==4) return true;
        if(v1==1&&v2==2) return true;
        
        return false;
    }
    
    public static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
}