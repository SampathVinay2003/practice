package GFG;

public class NonAdjMax {

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public int solve(Node root) {
        if (root == null) return 0;
        int rootExists = 0;
        rootExists += root.data;
        int rootNotExists = 0;
        if (root.left != null) {
            rootExists += solve(root.left.left);
            rootExists += solve(root.left.right);
        }

        if (root.right != null) {
            rootExists += solve(root.right.left);
            rootExists += solve(root.right.right);
        }
        rootNotExists = solve(root.left) + solve(root.right);
        return Math.max(rootExists, rootNotExists);
    }

    public int getMaxSum(Node root) {

        return solve(root);
    }
    public static  void main(String[] args) {
        NonAdjMax nonAdjMax = new NonAdjMax();
        Node root = new Node(11);
        root.left = new Node(2);
        root.right = new Node(3);
        nonAdjMax.getMaxSum(root);
    }
}
