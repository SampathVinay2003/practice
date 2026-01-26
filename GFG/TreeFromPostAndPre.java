package GFG;

import java.util.*;

public class TreeFromPostAndPre {
    static class Node {
        int data;
        Node left, right;
        Node(int d) { data = d; }
    }

    static int preIdx, postIdx;

    // Builds one valid tree assuming it's FULL (every node has 0 or 2 children).
    // Works in O(n) time.
    static Node buildFromPrePost(int[] pre, int[] post) {
        Node root = new Node(pre[preIdx++]);

        // If the current root hasn't been "closed" by post[postIdx],
        // we can still attach children in preorder sequence.
        if (root.data != post[postIdx]) {
            root.left = buildFromPrePost(pre, post);
        }
        if (root.data != post[postIdx]) {
            root.right = buildFromPrePost(pre, post);
        }

        // Now we've finished this subtree; advance postIdx past its root.
        postIdx++;
        return root;
    }

    // Optional: inorder print to verify shape
    static void printInorder(Node r) {
        if (r == null) return;
        printInorder(r.left);
        System.out.print(r.data + " ");
        printInorder(r.right);
    }

    public static void main(String[] args) {
        int[] pre  = {1, 2, 4, 8, 9, 5, 3, 6, 7};
        int[] post = {8, 9, 4, 5, 2, 6, 7, 3, 1};

        preIdx = 0;
        postIdx = 0;
        Node root = buildFromPrePost(pre, post);

        printInorder(root); // e.g., to check the reconstructed tree
    }
}
