/*
* 83. 删除排序链表中的重复元素
* */
public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode first = head;
        while (first.next != null && first != null) {
                if (first.val == first.next.val){
                    first.next = first.next.next;
                }else {
                    first = first.next;
                }
        }
        return head;
    }
}
class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}