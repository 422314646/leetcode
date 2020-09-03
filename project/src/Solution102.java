import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
*102. 二叉树的层序遍历
* */
public class Solution102 {

    /*List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return null;
        }
        helper(root, 0);
        return res;
    }

   public void helper(TreeNode node, int level){
        if (res.size() == level){
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);

        if (node.left != null){
            helper(node.left, level + 1);
        }
        if (node.right != null){
            helper(node.right, level + 1);
        }
   }*/

   //层序遍历
   public List<List<Integer>> levelOrder(TreeNode root) {
       List<List<Integer>> res = new ArrayList<>();
       if (root == null){
           return res;
       }
       Queue<TreeNode> queue = new ArrayDeque<>();

       queue.add(root);
       while (!queue.isEmpty()){
           int count = queue.size();
           List<Integer> number = new ArrayList<>();
           for (int i = 0; i < count; i++) {
               TreeNode node = queue.poll();
               number.add(node.val);

               if (node.left != null){
                   queue.add(node.left);
               }
               if (node.right != null){
                   queue.add(node.right);
               }
           }
           res.add(number);
       }
       return res;
   }
}
