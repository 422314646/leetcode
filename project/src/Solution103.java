import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import java.util.*;

/*
*103. 二叉树的锯齿形层次遍历
* */
public class Solution103 {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        Solution103 solution103 = new Solution103();
        solution103.zigzagLevelOrder(treeNode1);
        System.out.println(solution103.zigzagLevelOrder(treeNode1));

    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        return levelOrder(root);
    }

    //层序遍历
    public List<List<Integer>> levelOrder(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> number = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag){
                    number.addFirst(node.val);
                } else {
                    number.addLast(node.val);
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            flag = !flag;
            res.add(number);
        }
        return res;
    }
}
