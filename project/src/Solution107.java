import java.util.*;

/*
* 107. 二叉树的层次遍历 II
* */
public class Solution107 {
    public static void main(String[] args) {
        Solution107 solution107 = new Solution107();
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right= treeNode4;


        solution107.levelOrderBottom(treeNode);
        //System.out.println();
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> target = new ArrayList<>();
        if (root == null){
            return target;
        }
        List<List<Integer>> res = level(root);
        for (int i = 0; i < res.size(); i++) {
            target.add(res.get(res.size() - 1 - i));
            //System.out.println(target);
        }
        System.out.println(target);
        return target;
    }

    private List<List<Integer>> level(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        System.out.println(res);
        return res;
    }
}
