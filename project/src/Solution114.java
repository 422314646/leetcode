import java.util.ArrayList;
import java.util.List;

/*
* 114. 二叉树展开为链表
* */
public class Solution114 {

    public static void main(String[] args) {
        TreeNode treeNode0 = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);

        treeNode0.left = treeNode1;
        treeNode0.right = treeNode4;
        treeNode1.left= treeNode2;
        treeNode1.right = treeNode3;
        treeNode4.right = treeNode5;
        Solution114 solution114 = new Solution114();
        solution114.flatten(treeNode0);
    }
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode newRoot = list.get(i - 1), cur = list.get(i);
            newRoot.left = null;
            newRoot.right = cur;
        }
        //System.out.println(list);
    }

    public void preOrder(TreeNode treeNode, List<TreeNode> list){
        if (treeNode == null){
            return;
        }
        list.add(treeNode);
        if (treeNode.left != null){
            preOrder(treeNode.left, list);
        }
        if (treeNode.right != null){
            preOrder(treeNode.right, list);
        }

    }

    public void flatten1(TreeNode root){
        TreeNode cur = root;
        while (cur != null){
            if (cur.left != null){
                TreeNode next = cur.left;
                TreeNode pre = next;
                while (pre.right != null){
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }
}
