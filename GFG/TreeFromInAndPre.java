package GFG;

public class TreeFromInAndPre {
    static class Node{
        int data;
        Node left, right;
        public  Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    static int inorderIdx, preorderIdx;
    public static Node solve(int inorder[], int preorder[]){
        if(preorderIdx == preorder.length)return null;
        Node root = new Node(preorder[preorderIdx++]);
        if(root.data != inorder[inorderIdx]){
            root.left = solve(inorder, preorder);
        }

        if(root.data != inorder[inorderIdx]){
            root.right = solve(inorder, preorder);
        }
        inorderIdx++;
        return root;
    }
    public static Node buildTree(int inorder[], int preorder[]) {
        inorderIdx = 0;
        preorderIdx = 0;
        return solve(inorder, preorder);
    }

    public static void main(String[] args) {
        int[] inorder = {3, 1, 4, 0, 5, 2};
        int[] preorder = {0, 1, 3, 4, 2, 5};
        Node root = buildTree(inorder, preorder);
    }

}
