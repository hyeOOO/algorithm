import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static StringBuilder[] expr;
    public static StringBuilder[] result;
    public static String[] str;
    public static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        expr = new StringBuilder[N];
        result = new StringBuilder[N];
        str = new String[N];
        
        for (int i = 0; i < N; i++) {
            expr[i] = new StringBuilder();
            result[i] = new StringBuilder();
        }
        for (int i = 0; i < N; i++) {
            expr[i].append(br.readLine());
            str[i] = expr[i].toString();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < expr[i].length(); j++) {
                char c = expr[i].charAt(j);
                map.put(c, map.getOrDefault(c, 0) + (int)Math.pow(10, expr[i].length() - j - 1));
            }
        }
        
        Character[] chars = map.keySet().toArray(new Character[0]);
        Arrays.sort(chars, (a, b) -> map.get(b) - map.get(a));
        
        int num = 9;
        for (char c : chars) {
            map.put(c, num--);
        }
        
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                result[i].append(map.get(str[i].charAt(j)));
            }
            total += Integer.parseInt(result[i].toString());
        }
        System.out.println(total);
    }
}