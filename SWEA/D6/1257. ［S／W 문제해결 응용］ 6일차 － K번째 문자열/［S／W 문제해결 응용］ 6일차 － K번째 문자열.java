import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Solution
{
	static int K;
	static class Trie{
		char alphabet;
		boolean isWordEnd;
		int count = 0;
		Map<Character, Trie> children = new HashMap<>();
		
		Trie(){}
		
		Trie(Character alphabet){
			this.alphabet = alphabet;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			K = Integer.parseInt(br.readLine())+1;
			String words = br.readLine();
			int len = words.length();
			Trie head = new Trie();
			
			for(int i=0; i<len; i++) {
				if(i == len) continue;
				Trie indexTrie = head;
				insert(words, i, indexTrie);
			}
			
			char[] results = new char[len];
			dfs(head, 0, tc, results);
			if(K>0) {
				System.out.printf("#%d %s\n", tc, "none");
			}
		}
	}
	
	public static void dfs(Trie trie, int depth, int tc, char[] results) {
		if(K==0) return;
		K--;
		if(K==0) {
			String result = "";
			for(int i=0; i<depth; i++) {
				result += results[i];
			}
			System.out.printf("#%d %s\n", tc, result);
			return;
		}
		
		for(char i='a'; i<='z'; i++) {
			if(trie.children.containsKey(i)) {
				Trie child = trie.children.get(i);
				if(child.count<K) {
					K -= child.count;
					continue;
				}
				
				results[depth] = i;
				dfs(trie.children.get(i), depth+1, tc, results);
				results[depth] = '_';
			}
		}
	}
	
	public static int insert(String words, int idx, Trie trie) {
		// words[idx] 번 문자를 trie에 삽입
		if(idx == words.length()) return 0;
		
		char alphabet = words.charAt(idx);
		
		int subCount = 0;
		if(!trie.children.containsKey(alphabet)) {
			Trie newTrie = new Trie(alphabet);
			newTrie.count = 1;
			subCount = 1;
			trie.children.put(alphabet, newTrie);
		}
		
		subCount += insert(words, idx+1, trie.children.get(alphabet)); // 기존 트라이랑 다르게 구현한 이유
		trie.count += subCount;
		
		return subCount;
	}
}