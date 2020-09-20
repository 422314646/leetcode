import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/*
* 404. 左叶子之和
* */
public class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
    //广度
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left != null){
                if (isLeft(node.left)){
                    sum = sum + node.left.val;
                } else {
                    queue.add(node.left);
                }
            }
            if (node.right != null){
                if (!isLeft(node.right)){
                    queue.add(node.right);
                }
            }
        }
        return sum;
    }


    //深度
    public int dfs(TreeNode node){
        int sum = 0;
        if (node.left != null){
            sum += isLeft(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeft(node.right)){
            sum += dfs(node.right);
        }
        return sum;
    }

    private boolean isLeft(TreeNode root){
        return root.left == null && root.right == null;
    }
}
