import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int edge = Integer.parseInt(br.readLine());
        int arr[][] = new int[edge][2];
        
        /*input*/
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        /*cal*/
        double sum = 0;

        for (int i = 0; i < edge; i++) {
            sum += (double) arr[i][0] * arr[(i + 1) % edge][1]; 
            sum -= (double)arr[i][1] * arr[(i + 1) % edge][0];
        }

	/*print*/
        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}