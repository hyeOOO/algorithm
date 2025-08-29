import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, count;
    static int[] movingWalk;
    static boolean[] people;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        movingWalk = new int[2*N];
        people = new boolean[2*N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++){
            movingWalk[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(count);
    }

    public static void solution(){
        while(true){
            count++; // 횟수 증가

            // 1. 무빙워크 회전
            rotateMovingWalk();

            // 사람이 n번 째에 도달했으면 하차
            checkPeople();

            // 2. 사람들 이동 (뒤에서부터 처리)
            for(int i = 2*N-1; i >= 0; i--){
                if(people[i]){
                    int next = (i + 1) % (2*N); // 다음 칸 (원형)

                    // 내리는 위치가 아니고, 다음 칸이 비어있고, 내구도가 있으면 이동
                    if(!people[next] && movingWalk[next] > 0){
                        people[i] = false;
                        people[next] = true;
                        movingWalk[next]--;
                    }
                }
            }

            checkPeople();

            // 3. 사람 탑승
            if(movingWalk[0]>0 && !people[0]){
                movingWalk[0]--;
                people[0]=true;
            }

            // 사람이 n번 째에 도달했는지 검사
            checkPeople();

            // 4. 안정성 체크
            if(checkStability()>=K) break;
        }
    }

    public static void checkPeople(){
        if(people[N-1]){
            people[N-1] = false;
        }
    }

    public static void rotateMovingWalk(){
        int last = movingWalk[2*N-1];
        boolean lastPeople = people[2*N-1];

        for(int i=2*N-1; i>=1; i--){
            movingWalk[i] = movingWalk[i-1];
            people[i] = people[i-1];
        }

        movingWalk[0] = last;
        people[0] = lastPeople;
    }

    public static int checkStability(){
        int cnt = 0;
        for(int i=0; i<2*N; i++){
            if(movingWalk[i]<=0) cnt++;
        }

        return cnt;
    }
}
