class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int prevTime = attacks[0][0];
        int prevDamage = attacks[0][1];
        
        int curHealth = health-prevDamage;
        
        if(curHealth<=0) return -1;
        
        for(int i=1; i<attacks.length; i++){
            int attackTime = attacks[i][0];
            int damage = attacks[i][1];
                
            curHealth += (attackTime - prevTime - 1) * bandage[1]; // x값 누적
            curHealth += ((attackTime-prevTime-1) / bandage[0]) * bandage[2]; // y값 누적
            
            if(curHealth>health) curHealth = health;
            
            curHealth = curHealth - damage;
            
            System.out.printf("attackTime = %d, attackDamage = %d, curHealth = %d\n", attackTime, damage, curHealth);
            
            if(curHealth<=0) return -1;
            prevTime = attackTime;
        }
        
        return curHealth;
    }
}