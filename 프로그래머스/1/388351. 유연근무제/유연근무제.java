class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int num = schedules.length; // 사원 수
        int count = 0;
        
        for(int i=0; i<num; i++){ // 사원마다 체크
            int ruleTime = schedules[i]; // 사원마다 정해진 출근 시간
            boolean isAward = true;
            int currentDay = startday;
            
            for(int j=0; j<7; j++){
                if(currentDay != 6 && currentDay != 7 && timelogs[i][j] > addMinutes(ruleTime, 10)) {
                    isAward = false;
                    break;
                }
                 currentDay = currentDay % 7 + 1;
            }
            
            if(isAward) count++;
        }
        
        return count;
    }
    
    private int addMinutes(int time, int minutes){
        int hour = time/100;
        int min = time%100;
        
        min+=minutes;
        if(min>=60){
            hour += min/60;
            min = min%60;
        }
        
        return hour*100+min;
    }
}