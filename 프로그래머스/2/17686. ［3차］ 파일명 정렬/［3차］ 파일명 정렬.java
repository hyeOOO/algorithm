import java.util.*;

class Solution {
    static class File implements Comparable<File>{
        int idx;
        String head;
        String number;
        String tail;

        File(int idx, String head, String number, String tail){
            this.idx = idx;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String getFile() {
            return head + number + tail;
        }

        public int compareTo(File b){
            String aHead = this.head.toUpperCase();
            String bHead = b.head.toUpperCase();
            
            if(aHead.equals(bHead)){
                int aNum = Integer.parseInt(this.number);
                int bNum = Integer.parseInt(b.number);
                if(aNum == bNum) 
                    return this.idx - b.idx;
                else 
                    return aNum - bNum;
            }else 
                return aHead.compareTo(bHead);
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<File> fileList = new ArrayList<>();
        
        for(int i=0; i<files.length; i++){
            String head = "";
            String number = "";
            String tail = "";
            for(char c : files[i].toCharArray()){
                if(number.length()==0 && (Character.isLetter(c)||c==' '||c=='.'||c=='-')){
                    head += c;
                }else if(tail.length()==0 && c>='0' && c<='9'){
                    number+=c;
                }else{
                    tail+=c;
                }
            }
            fileList.add(new File(i, head, number, tail));
        }
        
        Collections.sort(fileList);
        
        for(int i=0; i<files.length; i++){
            answer[i] = fileList.get(i).getFile();
        }
        
        return answer;
    }
}