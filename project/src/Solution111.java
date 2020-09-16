import java.util.ArrayDeque;
import java.util.Queue;

/*
* 111. 二叉树的最小深度
* */
public class Solution111 {
    //深度优先
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }
        int minDepth1 = Integer.MAX_VALUE;
        if (root.left != null){
            minDepth1 = Math.min(minDepth(root.left), minDepth1);
        }
        if (root.right != null){
            minDepth1 = Math.min(minDepth(root.right), minDepth1);
        }
        return minDepth1 + 1;
    }

    //广度优先
    public int minDepth1(TreeNode root){
        if (root == null){
            return 0;
        }
        Queue<QueueNode> queueNodes = new ArrayDeque<QueueNode>();
        queueNodes.add(new QueueNode(root, 1));

       while (!queueNodes.isEmpty()){
            QueueNode queueNode = queueNodes.poll();

            if (queueNode.treeNode.left == null && queueNode.treeNode.right == null){
                return queueNode.depth;
            }
            if (queueNode.treeNode.left != null){
                queueNodes.add(new QueueNode(queueNode.treeNode.left, queueNode.depth + 1));
            }
            if (queueNode.treeNode.right != null){
                queueNodes.add(new QueueNode(queueNode.treeNode.right, queueNode.depth + 1));
            }
        }
        return 0;
    }

    //递归
    public int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.right == null && root.left == null){
            return 1;
        }
        int min = minDepth2(root.left);
        int min1 = minDepth2(root.right);

        if(root.left == null || root.right == null) return min + min1 + 1;
        return Math.min(min, min1 ) + 1;
    }

}

class QueueNode{
    TreeNode treeNode;
    Integer depth;

    public QueueNode(TreeNode treeNode, Integer depth) {
        this.treeNode = treeNode;
        this.depth = depth;
    }
}
