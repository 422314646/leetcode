/*
* 109. 有序链表转换二叉搜索树
* */
public class Solution109 {
    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        //globalHead = head;
        return helper(0, length - 1);
    }

    public int getLength(ListNode head){
        int flag = 0;
        while (head != null){
            flag++;
            head = head.next;
        }

        return flag;
    }

    public TreeNode helper(int left, int right){
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode();
        root.left = helper(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = helper(mid + 1, right);

        return root;
    }
}
