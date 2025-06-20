import java.util.*;

class Solution {
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int solution(int[][] land) {
        if(land == null || land.length == 0 || land[0].length == 0) {
            return 0;
        }
        
        int rows = land.length;
        int cols = land[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        // 각 열에서 얻을 수 있는 석유량을 저장
        int[] oilPerColumn = new int[cols];
        
        // 전체 맵을 한 번만 순회하면서 모든 석유 덩어리 처리
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    // 이 석유 덩어리가 포함하는 열들과 크기를 구함
                    boolean[] columnCheck = new boolean[cols];
                    int oilSize = dfs(i, j, land, visited, columnCheck);
                    
                    // 이 덩어리와 연결된 모든 열에 석유량 추가
                    for(int col = 0; col < cols; col++) {
                        if(columnCheck[col]) {
                            oilPerColumn[col] += oilSize;
                        }
                    }
                }
            }
        }
        
        // 최대 석유량 찾기
        int maxOil = 0;
        for(int oil : oilPerColumn) {
            maxOil = Math.max(maxOil, oil);
        }
        
        return maxOil;
    }
    
    private int dfs(int y, int x, int[][] land, boolean[][] visited, boolean[] columnCheck) {
        visited[y][x] = true;
        columnCheck[x] = true; // 현재 위치의 열 체크
        int count = 1;
        
        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            
            if(ny >= 0 && nx >= 0 && ny < land.length && nx < land[0].length 
               && land[ny][nx] == 1 && !visited[ny][nx]) {
                count += dfs(ny, nx, land, visited, columnCheck);
            }
        }
        
        return count;
    }
}