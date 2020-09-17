import java.util.ArrayDeque;
import java.util.Queue;

public class Solution112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        Queue<TreeNode> queueNode = new ArrayDeque<>();
        Queue<Integer> queueVal = new ArrayDeque<>();

        queueNode.add(root);
        queueVal.add(root.val);

        while (!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            Integer temp = queueVal.poll();
            if (node.left == null && node.right == null){
                if (temp == sum){
                    return true;
                }
                continue;
            }
            if (node.left != null){
                queueNode.add(node.left);
                queueVal.add(temp + node.left.val);
            }
            if (node.right != null){
                queueNode.add(node.right);
                queueVal.add(temp + node.right.val);
            }
        }

        return false;
    }

    public boolean hasPathSum1(TreeNode root, int sum){
        if (root == null){
            return false;
        }
        if (root.left == null && root.right == null){
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
