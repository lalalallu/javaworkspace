
//Definition for singly-linked list.
 public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }
    public ListNode add(ListNode l1, ListNode l2, int bit) {
        if (l1 == null && l2 == null && bit == 0) {
            return null;
        }
        int val = bit;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        ListNode node = new ListNode(val % 10);
        node.next = add(l1, l2, val / 10);
        return node;
    }
}
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p=new ListNode(0);
        ListNode tem=p;
        int bit=0;
        while (l1!=null||l2!=null)
        {
            int x,y;
            if (l1==null)
            {
                //缺位用0补
                x=0;
            }
            else
            {
                x= l1.val;
            }
            if (l2==null)
            {
                y=0;
            }
            else
            {
                y= l2.val;
            }
            int sum=x+y+bit;

            //求出进位
            bit=sum/10;

            //求出相加后的值
            sum=sum%10;

            //构造节点
            tem.next=new ListNode(sum);

            tem=tem.next;

            if (l1!=null)
            {
                l1=l1.next;
            }
            if (l2!=null)
            {
                l2=l2.next;
            }
        }
        if (bit==1)
        {
            //判断最后一为是否进位
            tem.next=new ListNode(1);
        }
        return p.next;
    }

}
