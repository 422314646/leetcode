import java.util.ArrayDeque;
import java.util.Queue;

/*
* 129. 求根到叶子节点数字之和
* */
public class Solution129 {

    Integer res = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        return bfs(root);
    }

    //广度优先遍历
    private int bfs(TreeNode root){
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> valQueue = new ArrayDeque<>();
        Integer sum = 0;
        nodeQueue.add(root);
        valQueue.add(root.val);

        while (!nodeQueue.isEmpty() && !valQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            Integer val = valQueue.poll();

            if (node.right == null && node.left == null){
                sum += val;
            } else {
                if (node.left != null){
                    nodeQueue.add(node.left);
                    valQueue.add(val * 10 + node.left.val);
                }
                if (node.right != null){
                    nodeQueue.add(node.right);
                    valQueue.add(val * 10 + node.right.val);
                }
            }
        }
        return sum;
    }

    //深度优先
    private void dfs(TreeNode root, int val){
        if (root != null){
            return;
        }
        if (root.left == null && root.right == null){
            res = res + val * 10;
        } else {
            if (root.left != null){
                dfs(root.left, val * 10 + root.left.val);
            }
            if (root.right != null){
                dfs(root.right, val * 10 + root.right.val);
            }
        }
    }
}
