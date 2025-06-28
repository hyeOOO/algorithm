class Solution {
    public int solution(int num) {
        int count = 0;
        long n = num;
        
        while(n!=1){
            if(count>=500) {
                count = -1;
                break;
            }
            
            if(n%2==0){
                n=n/2;
            }else{
                n = n*3+1;
            }
            
            count++;
        }
        
        return count;
    }

}