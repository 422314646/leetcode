public class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null && head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (ListNode l = head, r = head; l != null; r = l){
            while (l != null && l.val == r.val){
                l = l.next;
            }
            if (r.next == l){
                tail.next = r;
                tail = tail.next;
                tail.next = null;
            }
        }
        return dummy.next;
    }

}
