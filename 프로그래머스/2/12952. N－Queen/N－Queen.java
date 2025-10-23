class Solution {
    static boolean[][] arr;
    static int answer;
    
    public int solution(int n) {
        arr = new boolean[n][n];
        answer = 0;
        backtracking(0, n);    
        return answer;
    }
    
    static public void backtracking(int r, int n){
        if(r==n){
            // for(int i=0; i<n; i++){
            //     for(int j=0; j<n; j++){
            //         System.out.print(arr[i][j]?1:0);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            answer++;
            return;
        }
        
        for(int c=0; c<n; c++){
            if(!arr[r][c] && validCheck(r, c, n)){
                arr[r][c] = true;
                backtracking(r+1, n);
                arr[r][c] = false;
            }
        }
    }
    
    static public boolean validCheck(int y, int x, int n){
        int[] dy = {-1, -1, -1};
        int[] dx = {0, -1, 1};
        
        for(int d=0; d<3; d++){
            int cy = y;
            int cx = x;
            
            while(true){
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if(ny<0||nx<0||ny>=n||nx>=n) break;
                if(arr[ny][nx]) return false;
                cy = ny;
                cx = nx;
            }
        }
        
        return true;
    }
}