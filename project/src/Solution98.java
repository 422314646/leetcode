import java.util.ArrayList;
import java.util.List;

/*
* 98. 验证二叉搜索树
* */
public class Solution98 {

    List<Integer> res = new ArrayList<>();
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        return sortJudgment(root);
    }

    //中序遍历二叉搜索树
    public void infixOrder(TreeNode root){
        if (root.left != null){
            infixOrder(root.left);
        }
        res.add(root.val);
        if (root.right != null){
            infixOrder(root.right);
        }
    }
    
    //判断是否res按升序排序
    public boolean res(List<Integer> res){
        for (int i = 0; i < res.size() - 1; i++) {
            if (res.get(i) > res.get(i + 1)){
                return false;
            }
        }
        return true;
    }

    //中序排序并且完成比较
    public boolean sortJudgment(TreeNode root){
        if (root == null){
            return true;
        }
        if (!sortJudgment(root.left)){
            return false;
        }
        if (root.val <= pre){
            return false;
        }
        pre = root.val;
        return sortJudgment(root.right);
    }
}
