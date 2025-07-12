import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Solution
{
	static int K;
    static class Trie{
        char alphabet; // 해당 노드의 알파벳
        boolean isWordsEnd; // 해당 노드가 단어의 끝인지 판단
        int count; // 해당 노드를 거치는 단어의 수
        Map<Character, Trie> children = new HashMap<>(); // 알파벳을 연결하기 위한 해시맵

        Trie(char alphabet){
            this.alphabet = alphabet;
            this.count = 0;
        }

        Trie(){

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            K = Integer.parseInt(br.readLine());
            String words = br.readLine();
            int len = words.length();
            Trie head = new Trie();

            if(K>len){
                System.out.printf("#%d %s\n", tc, "none");
                continue;
            }
            for(int i=0; i<len; i++){
                Trie indexTrie = head;

                for(int j=i; j<len; j++){
                    char alpha = words.charAt(j);

                    if(!indexTrie.children.containsKey(alpha)){
                        Trie newTrie = new Trie(alpha);
                        indexTrie.children.put(alpha, newTrie);
                    }

                    indexTrie = indexTrie.children.get(alpha);
                    indexTrie.count++;
                }

                indexTrie.isWordsEnd = true;
            }

            char[] results = new char[len];
            dfs(head, 0, tc, results);
        }
    }

    // trie := 현재 방문 중인 정점
    // depth := 현재 깊이
    // test_case := 테스트 케이스 번호
    public static void dfs(Trie trie, int depth, int tc, char[] results){
        if(K==0) return;

        if(trie.isWordsEnd){
            K--;
            if(K==0){
                String result = "";
                for(int i=0; i<depth; i++){
                    result+=results[i];
                }
                System.out.printf("#%d %s\n", tc, result);
                return;
            }
        }

        for(char i='a'; i<='z'; i++){
            if(trie.children.containsKey(i)){
                Trie child = trie.children.get(i);
                if(child.count<K){
                    K-=child.count;
                    continue;
                }

                results[depth] = i;
                dfs(child, depth+1, tc, results);
                results[depth] = '_';
            }
        }
    }
}