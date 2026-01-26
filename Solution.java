import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
    private String best = "";

    public String longestValidWord(String[] words) {
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);
        dfs(trie.root, new StringBuilder());
        return best;
    }

    private void dfs(Trie.TrieNode node, StringBuilder path) {
        // If path length > 0, we’re on a letter node; count it only if it's a word end
        if (path.length() > 0 && node.isEnd) {
            String cand = path.toString();
            if (cand.length() > best.length() ||
                    (cand.length() == best.length() && cand.compareTo(best) < 0)) {
                best = cand;
            }
        }

        // Only descend into children that are ends of words (to preserve the prefix-valid property)
        for (int i = 0; i < 26; i++) {
            Trie.TrieNode child = node.children[i];
            if (child != null && child.isEnd) {
                path.append(child.c);
                dfs(child, path);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    class Trie {
        TrieNode root = new TrieNode('$');

        class TrieNode {
            char c;
            TrieNode[] children = new TrieNode[26];
            boolean isEnd = false;

            TrieNode(char c) { this.c = c; }
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a'; // expects lowercase 'a'..'z'
                if (idx < 0 || idx >= 26) {
                    throw new IllegalArgumentException("Only lowercase a-z are supported: " + word);
                }
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode(ch);
                cur = cur.children[idx];
            }
            cur.isEnd = true;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] words = new String[]{ "w","wo","wor","worl",
                "world", "b","br","bre","brea","break","breakf","breakfa","breakfast"};
        Solution sol = new Solution();
        String ans = sol.longestValidWord(words);
        System.out.println(ans);
    }
}
