package algoZenith.trie;

public class IntTrie extends Trie {
    int val;

    public IntTrie(int val) {
        this.val = val;
    }

    public void add(Integer number) {
        TrieNode tempRoot = root;
        for (int i = 31; i >= 0; i--) {
            int bit = number >> i & 1;
            if (tempRoot.children[bit] == null) {
                tempRoot.children[bit] = new TrieNode(bit);
            }
            tempRoot = tempRoot.children[bit];
            tempRoot.count++; // Increment count of current node
        }
    }
    public void remove(Integer number) {
        TrieNode tempRoot = root;
        for (int i = 31; i >= 0; i--) {
            int bit = number >> i & 1;
            if (tempRoot.children[bit] == null || tempRoot.children[bit].count == 0) {
                return; // Number doesn't exist
            }
            tempRoot = tempRoot.children[bit];
            tempRoot.count--; // Decrement count
        }
    }
    public int getMax(Integer number){
        TrieNode tempRoot = root;
        int maxNumber = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = number >> i & 1;
            int desiredBit = 1 - bit;
            
            // Try opposite bit first for maximum XOR
            if (tempRoot.children[desiredBit] != null && tempRoot.children[desiredBit].count > 0) {
                maxNumber = maxNumber | (1 << i); // XOR gives 1
                tempRoot = tempRoot.children[desiredBit];
            } else if (tempRoot.children[bit] != null && tempRoot.children[bit].count > 0) {
                //maxNumber = maxNumber | (0 << i); // XOR gives 0
                tempRoot = tempRoot.children[bit];
            }
        }
        return maxNumber;
    }

}
