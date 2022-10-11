package ssl;

import Node.Node;
import SinglyList_Node.*;

public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>
{
    protected boolean asc;                       //排序次序，取值为true（升序）或false（降序）

    public SortedSinglyList(boolean asc)         //构造空排序单链表，asc指定升/降序
    {
        super();                                 //构造空单链表，默认调用父类构造方法SinglyList()
        this.asc = asc;
    }
    public SortedSinglyList()                    //构造空排序单链表，默认升序
    {
        this(true);
    }

    //构造方法，按值插入values数组元素，asc指定升/降序。直接插入排序算法
    public SortedSinglyList(T[] values, boolean asc)
    {
        this(asc);                               //构造空排序单链表
        for(int i=0; i<values.length; i++)       //直接插入排序，每趟插入1个元素
            this.insert(values[i]);              //排序单链表按值插入，覆盖，O(n)
    }
    public SortedSinglyList(T[] values)          //构造方法，按值插入values数组元素，默认升序
    {
        this(values, true);
    }
    public Node<T> insert(T x)
    {
        if(x==null)
            return null;
        //以下循环寻找插入位置，插入在等值结点之后
        Node<T> front=this.head,  p=front.next;            //front指向p的前驱结点
        while(p!=null && (this.asc ? x.compareTo(p.data)>=0 : x.compareTo(p.data)<=0))
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                    //在front之后、p之前插入值为x结点
        return front.next;                                 //返回插入的x结点
    }
    public SortedSinglyList(SinglyList<T> list)
    {
        super(list);
        for(Node<T> first=this.head.next; first.next!=null; first=first.next)
        {
            Node<T> min=first;
            for(Node<T> p=min.next;   p!=null;   p=p.next)
                if(p.data.compareTo(min.data)<0)
                    min = p;
            if(min!=first)
            {
                T temp = min.data;
                min.data = first.data;
                first.data = temp;
            }
        }
    }
}
