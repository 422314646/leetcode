/*
*104. 二叉树的最大深度
* */
public class Solution104 {

    public static void main(String[] args) {
        Solution104 solution104 = new Solution104();
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;

        solution104.maxDepth(treeNode);

    }
    public int maxDepth(TreeNode root) {
       if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
