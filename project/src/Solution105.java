import java.util.HashMap;
import java.util.Map;

/*
* 105. 从前序与中序遍历序列构造二叉树
* */
public class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLength = preorder.length;
        int inLength = inorder.length;
        if (preLength != inLength){
            throw new RuntimeException("错误");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLength; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLength - 1, 0, inLength - 1, map);
    }

   private TreeNode buildTree(int[] preorder, int pL, int pR, int il, int iR, Map<Integer, Integer> map){
       if (pL > pR || il > iR){
           return null;
       }
       int value = preorder[pL];
       int pIndex = map.get(value);
       TreeNode root = new TreeNode(value);

       root.left = buildTree(preorder, pL + 1, pIndex - il + pL, il, pIndex - 1, map);
       root.right = buildTree(preorder, pIndex - il + pL + 1, pR, pIndex + 1, iR, map);

       return root;
    }
}
