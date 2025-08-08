class Solution {
    final int MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        
        // 잠긴 지역 처리
        for(int i=0; i<puddles.length; i++){
            int x = puddles[i][0];
            int y = puddles[i][1];
            
            dp[y][x] = -1;
        }
        
        dp[1][1] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(dp[i][j]==-1) continue; // 잠겼으면 pass
                if(dp[i-1][j]==-1 && dp[i][j-1]==-1) continue; // [i][j]로 올 수 있는 모든 길이 잠겼어도 pass
                if(dp[i-1][j]==-1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1])%MOD;
                    continue;
                }
                
                if(dp[i][j-1]==-1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j])%MOD;
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + dp[i][j-1])%MOD;
            }
        }
        
        return dp[n][m];
    }
}