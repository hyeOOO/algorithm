import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        // 전화번호 목록 정렬
        Arrays.sort(phone_book);
        
        // 정렬 후에는 인접한 두 번호만 비교하면 됨
        for(int i = 0; i < phone_book.length - 1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }
        
        return true;
    }
}