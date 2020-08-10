/*
* 92. 反转链表 II
* */
public class Solution92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode first = head;
        ListNode second = null;

        while (m > 1){
            second = first;
            first = first.next;
            m--;
            n--;
        }
        ListNode newHead = second;
        ListNode tail = first;
        ListNode third = null;
        while (n > 0){
            third = first.next;
            first.next = second;
            second = first;
            first = third;
            n--;
        }

        if (newHead != null){
            newHead.next = second;
        }else {
            head = second;
        }
        tail.next = first;
        return head;
    }
}
