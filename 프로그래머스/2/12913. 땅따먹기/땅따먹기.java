class Solution {
    int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        int[][] dp = new int[N][M];
        int answer = 0;
        
        for(int i=0; i<M; i++){
            dp[0][i] = land[0][i];
        }
        
        for(int i=1; i<N; i++){
            for(int j=0; j<M; j++){
                dp[i][j] = land[i][j]+getBest(i-1, j, N, M, dp);
            }
        }

        for(int i=0; i<M; i++){
            answer = Math.max(answer, dp[N-1][i]);
        }
        
        return answer;
    }
    
    public static int getBest(int y, int x, int n, int m, int[][] dp){
        int result = 0;
        for(int i=0; i<m; i++){
            if(x==i) continue;
            result = Math.max(result, dp[y][i]);
        }
        
        return result;
    }
}