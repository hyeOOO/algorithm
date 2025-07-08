import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	static int EXPONENT1 = 31;
	static int EXPONENT2 = 37;
	static int EXPONENT3 = 41;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            String B = br.readLine();
            String S = br.readLine();

            int answer = findString(B,S);
            System.out.printf("#%d %d\n", tc, answer);
        }
    }

    public static int findString(String b, String s){
        int result = 0;
        int parentSize = b.length();
        int patternSize = s.length();
        
        int parentHash1 = 0;
        int patternHash1 = 0;
        
        int parentHash2 = 0;
        int patternHash2 = 0;
        
        int parentHash3 = 0;
        int patternHash3 = 0;
        
        int power1 = 1;
        int power2 = 1;
        int power3 = 1;

        for(int i=0; i<=parentSize-patternSize; i++){
            if(i==0){
                for(int j=0; j<patternSize; j++){
                    parentHash1 = parentHash1 + b.charAt(patternSize-1-j)*power1;
                    patternHash1 = patternHash1 + s.charAt(patternSize-1-j)*power1;
                    
                    parentHash2 = parentHash2 + b.charAt(patternSize-1-j)*power2;
                    patternHash2 = patternHash2 + s.charAt(patternSize-1-j)*power2;
                    
                    parentHash3 = parentHash3 + b.charAt(patternSize-1-j)*power3;
                    patternHash3 = patternHash3 + s.charAt(patternSize-1-j)*power3;

                    if(j<patternSize-1) {
                    	power1 = power1*EXPONENT1;
                    	power2 = power2*EXPONENT2;
                    	power3 = power3*EXPONENT3;
                    }
                }
            }else {
                parentHash1 = EXPONENT1 * (parentHash1-b.charAt(i-1)*power1)+b.charAt(patternSize-1+i);
                parentHash2 = EXPONENT2 * (parentHash2-b.charAt(i-1)*power2)+b.charAt(patternSize-1+i);
                parentHash3 = EXPONENT3 * (parentHash3-b.charAt(i-1)*power3)+b.charAt(patternSize-1+i);
            }

            if(parentHash1 == patternHash1 && parentHash2 == patternHash2 && parentHash3 == patternHash3){
                result++;
            }
        }

        return result;
    }
	
}