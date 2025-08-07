class Solution {
    public int solution(String arr[]) {
        int n = (arr.length + 1) / 2; // 숫자 개수

        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];

        // 숫자는 그대로 채워 넣기
        for (int i = 0; i < n; i++) {
            maxDp[i][i] = Integer.parseInt(arr[2 * i]);
            minDp[i][i] = Integer.parseInt(arr[2 * i]);
        }
        
        for(int len = 2; len <= n; len++){ // 현재 계산하려는 구간의 길이
            for(int i = 0; i <= n-len; i++){ // 구간의 시작점
                int j = i + len - 1;
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
                
                // i~j 사이의 연산자를 기준으로 분할
                for (int k = i; k < j; k++) {
                    String op = arr[2 * k + 1];

                    int[] candidates = new int[] {
                        calc(maxDp[i][k], maxDp[k + 1][j], op),
                        calc(maxDp[i][k], minDp[k + 1][j], op),
                        calc(minDp[i][k], maxDp[k + 1][j], op),
                        calc(minDp[i][k], minDp[k + 1][j], op),
                    };

                    for (int val : candidates) {
                        maxDp[i][j] = Math.max(maxDp[i][j], val);
                        minDp[i][j] = Math.min(minDp[i][j], val);
                    }
                }
            }
        }
        
        return maxDp[0][n-1];
    }
    
    int calc(int a, int b, String op) {
        if (op.equals("+")) return a + b;
        else return a - b;
    }
}