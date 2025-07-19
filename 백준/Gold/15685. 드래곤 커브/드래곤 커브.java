import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][] dots = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        while(N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> directions = getDirection(d, g);
            draw(y, x, directions);
        }

        int answer = getSquare();
        System.out.println(answer);
    }

    public static int getSquare(){
        int count = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(dots[i][j]&&dots[i+1][j]&&dots[i][j+1]&&dots[i+1][j+1]){
                    count++;
                }
            }
        }

        return count;
    }

    public static void draw(int y, int x, List<Integer> dir){
        dots[y][x] = true;
        for(int d : dir){
            if(d==0){
                dots[y][++x] = true;
            }else if(d==1){
                dots[--y][x] = true;
            }else if(d==2){
                dots[y][--x] = true;
            }else if(d==3){
                dots[++y][x] = true;
            }
        }
    }

    public static List<Integer> getDirection(int d, int g){
        List<Integer> list = new ArrayList<>();
        list.add(d);

        for(int i=0; i<g; i++){
            for(int j=list.size()-1; j>=0; j--){
                int next = (list.get(j)+1)%4;
                list.add(next);
            }
        }

        return list;
    }
}
