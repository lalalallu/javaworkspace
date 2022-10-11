import DoubleNode.DoubleNode;

public class CirdoublyList<T> {
    public DoubleNode<T> head;

    //public int count=0;
    public CirdoublyList() {
        this.head = new DoubleNode<T>();
        this.head.prev = this.head;
        this.head.next = this.head;
        //count++;
    }

    public CirdoublyList(T x) {
        this();
        this.head.data = x;
    }

    public CirdoublyList(CirdoublyList<T> list)  //深拷贝构造方法，复制循环双链表
    {
//        this();                                  //创建空循环双链表，只有头结点
//        this.copy(list);
        this();
        this.head.data = list.head.data;
        DoubleNode<T> p = list.head.next;
        while (p != list.head) {
            this.insert(p.data);
            p = p.next;
        }
    }

    //    public void copy(CirdoublyList<T> list)      //复制list所有元素到this，放弃this原结点。O(n)
//    {
//        this.clear();                            //设置this为空链表
//        DoubleNode<T> rear = this.head;
//        for(DoubleNode<T> p=list.head.next;  p!=list.head;  p=p.next)
//        {
//            rear.next = new DoubleNode<T>(p.data, rear, this.head);
//            rear = rear.next;
//        }
//        this.head.prev = rear;
//    }
//    public void clear()                          //删除循环双链表所有元素
//    {
//        this.head.prev = this.head;
//        this.head.next = this.head;
//    }
    public boolean isEmpty() {
        return this.head.next == this.head;
    }

    public DoubleNode<T> insert(int i, T x) {
        if (x == null) {
            return null;
        }
        DoubleNode<T> front = this.head;
        for (int j = 0; front.next != this.head && j < i; j++) {
            front = front.next;
        }
        DoubleNode<T> q = new DoubleNode<T>(x, front, front.next);
        front.next.prev = q;
        front.next = q;
        //count++;
        return q;
    }

    public DoubleNode<T> insert(T x) {
        if (x == null) {
            return null;
        }
        DoubleNode<T> q = new DoubleNode<T>(x, head.prev, head);
        head.prev.next = q;
        head.prev = q;
        //count++;
        return q;
    }

    //    public T remove(int i)
//    {
//
//    }
    public String toString() {
        String str = this.getClass().getName() + ":";
        DoubleNode<T> p = this.head;
        str += p.data.toString() + " ";
        for (DoubleNode<T> q = this.head.next; q != null; q = q.next) {
            if (p == q) {
                break;
            }
            str += q.data.toString() + " ";
        }
        return str;
    }

    public void concat(CirdoublyList<T> list) {
        DoubleNode<T> tem;
        this.head.prev.next = list.head;
        list.head.prev.next = this.head;
        tem = list.head.prev;
        list.head.prev = this.head.prev;
        this.head.prev = tem;
        list.head = null;
        //this.count=this.count+ list.count;
    }

    //    public DoubleNode<T> search(CirdoublyList<T> pattern)  	//查找首个与pattern匹配的子表
//    {
//        CirdoublyList<T> temp=new CirdoublyList<>(this);
//        DoubleNode<T> p=pattern.head.next;
//        DoubleNode<T> q=temp.head.next;
//        DoubleNode<T> p1=pattern.head;
//        DoubleNode<T> q1=temp.head;
////        do
////        {
////            if(temp.equals(pattern))
////            {
////                return q;
////            }
////            temp.head.prev.next = temp.head.next;
////            temp.head.next=null;    //删除p结点，由JVM稍后释放
////            temp.head= q;
////            temp.head.prev=p;
////        }while (q!=this.head);
////        int count=0;
//        while (q!=temp.head)
//        {
//                while (p1.next!=pattern.head&&q1.next!=temp.head)
//                {
//                    if ((q.equals(p)))
//                    {
//                        p=p.next;
//                        q=q.next;
//
//                    }
//                    else
//                    {
//
//                    }
//                }
//
//        }
//        return null;
//    }
//    public CirdoublyList<T> search(CirdoublyList<T> pattern)  	//查找首个与pattern匹配的子表
//    {
//        CirdoublyList<T> temp=new CirdoublyList<>(this);
////        System.out.println(temp.head.next);
//        DoubleNode<T> p=pattern.head.next;
//        DoubleNode<T> q=temp.head.next;
//        DoubleNode<T> p1=pattern.head;
//        DoubleNode<T> q1=temp.head;
//
////      DoubleNode<T> h=temp.head;
////        h.next=null;
////        do
////        {
////            if(temp.equals(pattern))
////            {
////                return temp;
////            }
////            temp.head.prev.next=temp.head.next;
////            temp.head.prev.next.prev=p;
////            temp.head.prev=null;
////            temp.head.next=null;
////            temp.head=q;
////            q=temp.head.next;
////        }while (q!=temp.head);
//        int count=0;
//        while (q!=temp.head)
//        {
//            if(p1==q1)
//            {
//                count++;
//                if(p1.next==pattern.head)
//                {
//
//                }
//                p1=p1.next;
//                q1=q1.next;
//            }
//        }
//        return null;
//    }
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CirdoublyList<?>))
            return false;
        DoubleNode<T> p = this.head.next;
        CirdoublyList<T> list = (CirdoublyList<T>) obj;
        DoubleNode<T> q = list.head.next;
        //System.out.println(p);
        if (this.head.equals(list.head)) {
            while (p != head && q != list.head && p.data.equals(q.data)) {
                p = p.next;
                q = q.next;
            }
        }
        return p == head && q == list.head;
    }
}

class CirdoublyList_ex {
    public static void main(String[] args) {
        CirdoublyList<Integer> c1 = new CirdoublyList<>(1);
        c1.insert(2);
        c1.insert(3);
        c1.insert(32);
        c1.insert(3);
        c1.insert(2);
        c1.insert(3);
        System.out.println(c1);
        CirdoublyList<Integer> c2 = new CirdoublyList<>(3);
        c2.insert(32);
        //c1.concat(c2);
        System.out.println(c2);
        //System.out.println(c1);
        //System.out.println(c1.search(c2));
    }
}
