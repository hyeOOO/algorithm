import java.util.*;

class Solution {
    static int N, M;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        
        for(int i=0; i<N; i++){
            map[i] = maps[i].toCharArray();
        }
        //출발지->레버까지의 최단시간
        int[] t1 = bfs1();
        if(t1[0]==-1) return -1;
        //레버->탈출구까지의 최단시간
        int[] t2 = bfs2(t1[1], t1[2]);
        if(t2[0]==-1) return -1;
        
        return t1[0]+t2[0];
    }
    
    public int[] bfs2(int sy, int sx){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{sy, sx, 0});
        visited[sy][sx] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(map[cur[0]][cur[1]]=='E') return new int[]{cur[2], cur[0], cur[1]};
            
            for(int d=0; d<4; d++){
                int ny = cur[0]+dy[d];
                int nx = cur[1]+dx[d];
                
                if(ny<0||nx<0||ny>=N||nx>=M||visited[ny][nx]
                  ||map[ny][nx]=='X') continue;
                
                visited[ny][nx]=true;
                q.offer(new int[]{ny,nx,cur[2]+1});
            }
        }
        
        return new int[]{-1, -1, -1};
    }
    
    public int[] bfs1(){
        int[] starts = getStart();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        if(starts[0]==-1&&starts[1]==-1) return new int[]{-1,-1,-1};
        
        q.offer(new int[]{starts[0], starts[1], 0});
        visited[starts[0]][starts[1]] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(map[cur[0]][cur[1]]=='L') return new int[]{cur[2], cur[0], cur[1]};
            
            for(int d=0;d<4; d++){
                int ny = cur[0]+dy[d];
                int nx = cur[1]+dx[d];
                
                if(ny<0||nx<0||ny>=N||nx>=M||visited[ny][nx]
                  ||map[ny][nx]=='X') continue;
                
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx, cur[2]+1});
            }
        }
        
        return new int[]{-1,-1,-1};
    }
    
    public int[] getStart(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]=='S') return new int[]{i, j};
            }
        }
        
        return new int[]{-1,-1};
    }
}