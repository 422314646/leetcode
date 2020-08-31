/*
* 100. 相同的树
* */
public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null){
            return false;
        }
        if (q.val != p.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right , q.right);
    }
}
