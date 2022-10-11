package SinglyList_Node;

import Node.Node;

public class SinglyList <T> extends Object
{
    public Node<T> head;
    public SinglyList()
    {
        this.head=new Node<T>();
    }
    public SinglyList(T[] values)
    {
        this();
        Node<T> rear=this.head;
        for (int i=0;i<values.length;i++)
        {
            if(values[i]!=null)
            {
                rear.next=new Node<T>(values[i],null);
                rear=rear.next;
            }
        }
    }
    public SinglyList(SinglyList<T> list)//深拷贝
    {
        this();
        //this.head.data = list.head.data;
        Node<T> no1 = list.head; // 沿着list链表遍历
        Node<T> no2 = this.head; // 沿着当前链表开始创建节点，然后拷贝
        while (no1.next != null)
        {
            no1 = no1.next;
            Node<T> p = new Node<T>();
            p.data = no1.data;//新节点的值为no1的值
            no2.next = p;
            no2=no2.next;
        }

    }
    public static <T> void reverse(SinglyList<T> list)//反转
    {
        if(list.head==null||list.head.next==null)
        {
            return ;
        }
        Node<T> pre=null;
        Node<T> last=null;
        while (list.head.next!=null)
        {
            last=list.head.next.next;
            list.head.next.next=pre;
            pre=list.head.next;
            if(last==null)
            {
                break;
            }
            list.head.next=last;
        }

    }
    public boolean isEmpty()
    {
        return this.head.next==null;
    }
    public T get(int i)
    {
        Node<T> p=this.head.next;
        for (int j=0;p!=null&&j<i;j++)
        {
            p=p.next;
        }
        return (i>=0&&p!=null)?p.data:null;
    }
    //    public void set(int i,T x)
//    {
//
//    }
//    public int size()
//    {
//        return 0;
//    }
    public String toString()
    {
        String str=this.getClass().getName()+"(";
        for (Node<T> p = this.head.next; p!=null; p=p.next)
        {
            str+=p.data.toString()+(p.next!=null?",":"");
        }
        return str+")";
    }
    public Node<T> insert(int i, T x)
    {
        if(x==null)
        {
            return null;
        }

        Node<T> front=this.head;
        for (int j=0;front.next!=null&&j<i;j++)
        {
            front=front.next;
        }
        front.next=new Node<T>(x,front.next);
        return front.next;
    }
    public Node<T> insert(T x)
    {
        return insert(Integer.MAX_VALUE,x);
    }
    public T remove(int i)
    {
        Node<T> front=this.head;
        for (int j=0;front.next!=null&&j<i;j++)
        {
            front=front.next;
        }
        if (i>=0&&front.next!=null)
        {
            T x=front.next.data;
            front.next=front.next.next;
            return x;
        }
        return null;
    }
    public void clear()
    {
        this.head.next=null;
    }
//    public SinglyList.Node<T> search()
//    {
//
//    }
//    public T remove(T key)
//    {
//
//    }
}
class SinglyList_ex
{
    public static void main(String[] args)
    {
        Integer[] tem=new Integer[5] ;
        for (int i=0;i<5;i++)
        {
            tem[i]=i+1;
        }
        SinglyList<Integer> p=new SinglyList<>(tem);
        System.out.println(p);
        SinglyList<Integer> pp=new SinglyList<Integer>(p);
        System.out.println(pp);
        SinglyList.reverse(pp);
        System.out.println(pp);
    }
}
