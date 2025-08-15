import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ID = 1;
    static int N, M, score;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class BlockGroup implements Comparable<BlockGroup>{
        int id;
        int blockCount;
        int rainbowBlock;
        int readerBlockY;
        int readerBlockX;
        List<int[]> blocks;

        BlockGroup(int id, int bCnt, int rCnt, int y, int x, List<int[]> blocks){
            this.id = id;
            this.blockCount = bCnt;
            this.rainbowBlock = rCnt;
            this.readerBlockY = y;
            this.readerBlockX = x;
            this.blocks = blocks;
        }

        public int compareTo(BlockGroup b){
            if(this.blockCount!=b.blockCount){
                return b.blockCount-this.blockCount;
            }
            if(this.rainbowBlock!=b.rainbowBlock){
                return b.rainbowBlock-this.rainbowBlock;
            }
            if(this.readerBlockY!=b.readerBlockY){
                return b.readerBlockY-this.readerBlockY;  // 큰 행부터
            }
            return b.readerBlockX-this.readerBlockX;      // 큰 열부터
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            List<BlockGroup> groups = findGroup();
            if (groups.isEmpty()) {
                break;
            }

            removeGroup(groups);
            gravity();
            rotateArray();
            gravity();
        }

        System.out.println(score);
    }

    public static void rotateArray(){
        int[][] tempArr = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                tempArr[N-1-j][i] = map[i][j];
            }
        }

        map = tempArr;  // 직접 할당으로 간단하게
    }

    public static void gravity(){
        for(int i=0; i<N; i++){
            int floor = N-1;
            List<Integer> pos = new ArrayList<>();
            int[] col = new int[N];
            Arrays.fill(col, 9);

            for(int j=N-1; j>=0; j--){
                if(map[j][i]==9) continue;

                if(map[j][i]==-1){ // -1을 만나면 그 전에 쌓였던 블록들을 바닥으로 다 내려야함
                    for(int p : pos){
                        col[floor--] = map[p][i];
                    }

                    col[j] = -1;
                    floor = j-1;
                    pos.clear();
                    continue;
                }

                pos.add(j);
            }

            for(int p : pos){
                if(floor<0) break;
                col[floor--] = map[p][i];
            }

            for(int j=0; j<N; j++){
                map[j][i] = col[j];
            }
        }
    }

    public static void removeGroup(List<BlockGroup> groups){
        BlockGroup group = groups.get(0);
        score += (group.blockCount*group.blockCount);

        for(int[] coord: group.blocks){
            int y = coord[0];
            int x = coord[1];

            map[y][x] = 9;
        }
    }

    public static List<BlockGroup> findGroup(){
        List<BlockGroup> blockGroups = new ArrayList<>();
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]) continue;
                if(map[i][j]==-1||map[i][j]==9||map[i][j]==0) continue;

                List<int[]> blocks = bfs(i, j, map[i][j]);

                if(blocks.size()>=2){ // 블록 그룹의 크기가 2 이상
                    int rainbowBlockCount = 0;
                    int[] leader = null;

                    // 무지개 블록 개수 세기 & 리더 블록 찾기
                    for(int[] coord : blocks){
                        int y = coord[0];
                        int x = coord[1];

                        if(map[y][x]==0){
                            rainbowBlockCount++;
                            visited[y][x] = false;  // 무지개 블록은 방문 해제
                        } else {
                            // 일반 블록 중에서 리더 블록 찾기 (행이 작고, 열이 작은 것)
                            if(leader == null || y < leader[0] || (y == leader[0] && x < leader[1])){
                                leader = new int[]{y, x};
                            }
                        }
                    }

                    if(leader != null){
                        blockGroups.add(new BlockGroup(ID++, blocks.size(), rainbowBlockCount, leader[0], leader[1], blocks));
                    }
                }
            }
        }

        Collections.sort(blockGroups);

        return blockGroups;
    }

    public static List<int[]> bfs(int y, int x, int color){
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        boolean includeNormalBlock = (color>0);

        q.offer(new int[]{y, x});
        visited[y][x] = true;
        result.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d=0; d<4; d++){
                int ny = cur[0]+dy[d];
                int nx = cur[1]+dx[d];

                if(ny<0||nx<0||ny>=N||nx>=N) continue; // 범위 벗어날때
                if(visited[ny][nx]) continue; // 방문했을 때
                if(map[ny][nx]==-1||map[ny][nx]==9) continue; // 검은색 블록이거나 빈칸일 때
                if(map[ny][nx]!=0 && map[ny][nx]!=color) continue; // 무지개 블록도 아니고 같은 그룹 블록이 아니라면

                if(!includeNormalBlock) {
                    includeNormalBlock = (map[ny][nx]>0);
                }


                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
                result.add(new int[]{ny, nx});
            }
        }

        if(!includeNormalBlock) {
            result.clear();
        }
        return result;
    }
}
