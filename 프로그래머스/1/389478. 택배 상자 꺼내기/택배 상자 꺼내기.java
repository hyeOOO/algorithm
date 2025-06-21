class Solution {
    public int solution(int n, int w, int num) {
        // 1. 꺼내야 하는 박스의 위치 계산
        int height = ((num-1)/w) + 1;  // 층수 (1-based)
        int width = (height%2 == 1) ? (num-1)%w+1 : height*w-num+1;  // 홀수면 왼쪽->오른쪽, 짝수면 오른쪽->왼쪽
        
        int now = num;
        int count = 0;
        
        // 2. 같은 위치에 쌓인 상자들을 위로 올라가며 계산
        while(now <= n) {
            count++; // 다음 층
            
            // 3. 다음 층의 같은 위치 상자 번호 계산
            if((height + count)%2 == 0) {
                // 다음 층이 짝수층 (오른쪽→왼쪽)인 경우
                now = now + (w*2 - (width*2 - 1));
            } else {
                // 다음 층이 홀수층 (왼쪽→오른쪽)인 경우  
                now = now + (width * 2 - 1);
            }
        }

        return count;
    }
}