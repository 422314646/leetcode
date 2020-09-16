import java.util.ArrayList;
import java.util.List;

/*
*94. 二叉树的中序遍历
*  */
public class Solution94 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        preOder(root, res);
        return res;
    }

    public void preOder(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        } else {
            preOder(root.left, res);
            res.add(root.val);
            preOder(root.right, res);
        }
    }
}

class TreeNode {
     int val;
     TreeNode left;

    public TreeNode() {
    }

    TreeNode right;
     TreeNode(int x) { val = x; }
}