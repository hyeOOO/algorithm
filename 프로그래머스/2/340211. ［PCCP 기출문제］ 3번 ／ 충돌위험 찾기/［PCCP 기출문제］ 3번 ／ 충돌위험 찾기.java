import java.util.*;

class Solution {
    static int N, X, result;
    static int[][] robotMap;
    static int[][] robots;
    static int[] currentTarget;
    static int[] exited;
    
    public int solution(int[][] points, int[][] routes) {
        N = points.length;
        X = routes.length;
        robotMap = new int[101][101];
        robots = new int[X][2];
        currentTarget = new int[X];
        exited = new int[X];
        result = 0;
        
        // 초기 위치 설정 (각 로봇의 첫 번째 목표 포인트)
        for(int i = 0; i < X; i++) {
            int startPoint = routes[i][0];
            int y = points[startPoint - 1][0] - 1;
            int x = points[startPoint - 1][1] - 1;
            robots[i][0] = y;
            robots[i][1] = x;
            robotMap[y][x]++;
            currentTarget[i] = 1; // 다음 목표는 routes[i][1]
        }
        
        // 초기 충돌 체크
        checkCollision();
        
        // 시뮬레이션
        simulation(points, routes);
        
        return result;
    }
    
    public void simulation(int[][] points, int[][] routes) {
        while(true) {
            // 1. 탈출 여부 검사
            int escapeCount = 0;
            for(int i = 0; i < X; i++) {
                escapeCount += exited[i];
            }
            
            if(escapeCount == X) break;
            
            // 2. 이동
            for(int idx = 0; idx < X; idx++) {
                if(exited[idx] == 1) continue;
                
                int cy = robots[idx][0];
                int cx = robots[idx][1];
                
                // 모든 경로를 완주했는지 체크
                if(currentTarget[idx] >= routes[idx].length) {
                    robotMap[cy][cx]--;
                    exited[idx] = 1;
                    continue;
                }
                
                // 현재 목표 포인트
                int targetPoint = routes[idx][currentTarget[idx]];
                int gy = points[targetPoint - 1][0] - 1;
                int gx = points[targetPoint - 1][1] - 1;
                
                int nextY = cy;
                int nextX = cx;
                
                // r 좌표 우선 이동
                if(cy != gy) {
                    nextY = cy + (gy > cy ? 1 : -1);
                } else if(cx != gx) {
                    nextX = cx + (gx > cx ? 1 : -1);
                }
                
                // 위치 업데이트
                robotMap[cy][cx]--;
                robots[idx][0] = nextY;
                robots[idx][1] = nextX;
                robotMap[nextY][nextX]++;
                
                // 목표 도달 시 다음 목표로
                if(nextY == gy && nextX == gx) {
                    currentTarget[idx]++;
                }
            }
            
            // 3. 충돌 체크
            checkCollision();
        }
    }
    
    public void checkCollision() {
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                if(robotMap[i][j] > 1) {
                    result++;
                }
            }
        }
    }
}