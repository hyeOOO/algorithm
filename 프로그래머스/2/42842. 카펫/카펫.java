class Solution {
    public int[] solution(int brown, int yellow) {
        int width = -1;
        int height = -1;
        
        for(int w=3; w<=5000; w++){
            for(int h=3; h<=w; h++){
                int yBlock = (w-2)*(h-2);
                int bBlock = (w*h)-yBlock;
                if(yBlock == yellow && bBlock == brown){
                    if(w > width) {
                        width = w;
                        height = h;
                    } 
                }
            }
        }
        
        return new int[]{width, height};
    }
}