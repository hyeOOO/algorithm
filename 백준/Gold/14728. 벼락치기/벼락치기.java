import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        int n = scan.nextInt();
        int t = scan.nextInt();
 
        Node[] node = new Node[n + 1]; 
        for(int i = 1; i <= n; i++) {
            int k = scan.nextInt();
            int s = scan.nextInt();
            node[i] = new Node(k, s);
        }
 
        int[][] dp = new int[n + 1][t + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= t; j++) {
                if(node[i].time <= j){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - node[i].time] + node[i].score);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][t]);
    }
 
    public static class Node {
        int time;
        int score;
 
        public Node(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }
}
