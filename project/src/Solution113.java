import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* 113. 路径总和 II
* */
public class Solution113 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null){
            return res;
        }
        LinkedList<Integer> list = new LinkedList<>();
        helper(root, list, sum);
        return res;
    }

    public void helper(TreeNode root, LinkedList list, int sum){
        list.addLast(root.val);
        sum = sum - root.val;
        if (root.left == null && root.right == null && sum == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        if (root.left != null){
            helper(root.left, list, sum);
            list.removeLast();
        }
        if (root.right != null){
            helper(root.right, list, sum);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode4_ = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode5_ = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);

        treeNode5.left = treeNode4;
        treeNode5.right = treeNode8;
        treeNode4.left = treeNode11;
        treeNode11.left = treeNode7;
        treeNode11.right = treeNode2;
        treeNode8.left = treeNode13;
        treeNode8.right = treeNode4_;
        treeNode4_.left = treeNode5_;
        treeNode4_.right = treeNode1;

        Solution113 solution = new Solution113();
        List<List<Integer>> res = solution.pathSum(treeNode5, 22);
        System.out.println(res);
    }
}
