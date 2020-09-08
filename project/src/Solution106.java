import java.util.HashMap;
import java.util.Map;

/*
* 从中序与后序遍历序列构造二叉树
* */
public class Solution106 {
    public static void main(String[] args) {
        Solution106 solution106 = new Solution106();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        solution106.buildTree(inorder, postorder);
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLength = inorder.length;
        int postLength = postorder.length;
        if (postLength != inLength){
            throw new RuntimeException("错误");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLength; i++) {
            map.put(inorder[i], i);
        }
        return buildTree1(postorder, 0, postLength - 1, 0, inLength - 1, map);
    }

    private TreeNode buildTree1(int[] preorder, int pL, int pR, int il, int iR, Map<Integer, Integer> map){
        if (pL > pR || il > iR){
            return null;
        }
        int value = preorder[pR];
        int pIndex = map.get(value);
        TreeNode root = new TreeNode(value);

        root.left = buildTree1(preorder, pL, pIndex - 1 - il + pL, il, pIndex - 1, map);
        root.right = buildTree1(preorder, pIndex - il + pL, pR - 1, pIndex + 1, iR, map);

        return root;
    }

}
