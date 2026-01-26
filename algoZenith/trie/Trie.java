package algoZenith.trie;

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('#');
    }

    static class TrieNode {
        char ch;
        TrieNode[] children;
        boolean isEnd;
        int count;
        int bit;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[32];
            isEnd = false;
            count = 0;
        }
        public TrieNode(int bit) {
            this.bit = bit;
            children = new TrieNode[2];
            isEnd = false;
            count = 0;
        }
    }

    public void add(String string) {
        TrieNode tempRoot = root;
        int i = 0;
        while (tempRoot != null && i < string.length()) {
            char c = string.charAt(i++);
            if (tempRoot.children[c - 'a'] == null) {
                tempRoot.children[c - 'a'] = new TrieNode(c);
            }
            tempRoot = tempRoot.children[c - 'a'];
            tempRoot.count++;
        }
        tempRoot.isEnd = true;
    }
    public int countPrefix(String string){
        TrieNode tempRoot = root;
        int i = 0;
        while (tempRoot != null && i < string.length()) {
            char c = string.charAt(i++);
            if (tempRoot.children[c - 'a'] == null) {
                return 0;
            }
            tempRoot = tempRoot.children[c - 'a'];
        }
        return tempRoot.count;
    }
}

