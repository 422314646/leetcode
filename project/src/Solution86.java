
/*
* 86. 分隔链表
* */
public class Solution86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode dummy1 = new ListNode(0);
        ListNode tail = dummy;
        ListNode tail1 = dummy1;
        ListNode flag = head;
        while (flag != null){
            if (flag.val < x){
                tail.next = flag;
                tail = tail.next;
                tail.next = null;
            }else {
                tail1.next = flag;
                tail1 = tail1.next;
                tail1.next = null;
            }
            flag = flag.next;
        }
        tail.next = dummy1.next;
        return dummy;
    }
}
