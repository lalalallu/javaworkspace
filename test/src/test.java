public class test
{
    public static void main(String[] args)
    {
        ListNode head=new ListNode(1);
        ListNode p=new ListNode();
        head.next=p;
        p.val =9;
        head.deleteDou(head);
    }

}
class ListNode {
    int val;
    ListNode next;
    ListNode()
    {
        this.val = -1;
    }
    ListNode(int data) {
        this.val = data;
    }
    public ListNode deleteDou(ListNode head)
    {
        ListNode p1 ;
        ListNode p2 ;
        while (head != null && head.val % 2 == 0)
        {//可以去除开头不符合条件的节点
            head = head.next;
        }
        if(head==null)
        {
            return null;
        }
        //不考虑这个情况会引起段错误
        p1 = head;
        p2 = head.next;
        while (p2 != null)
        {
            if (p2.val % 2 == 0)
            {
                p1.next = p2.next;
                p2 = p1.next;
            }
            else
            {
                p1 = p2;
                p2 = p2.next;
            }
        }

        return head;

//        if (pHead == null) {
//            return null;
//        }
//        int len = pHead.data;
//        ListNode p = pHead;
//        if (len % 2 == 0) {
//            while (p.next != null && p.next.next != null)
//            {
//                p.next = p.next.next;
//                p = p.next;
//                if (p.next != null && p.next.next == null)
//                {
//                    p.next = null;
//                }
//            }
//        }
//        else
//        {
//            while (p.next != null && p.next.next != null)
//            {
//                p.next = p.next.next;
//                p = p.next;
//            }
//        }
//        return pHead;

    }
}

