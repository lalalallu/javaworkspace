package BinarySortTree;

public class BinarySortTree<T extends Comparable<? super T>>
{
    public BinarySortNode root;
    public BinarySortTree()
    {
        this.root=null;
    }
    public BinarySortTree(T[] values)
    {
        this();
        for (int i=0;i<values.length;i++)
        {
            this.add(values[i]);
        }
    }
    public boolean add (T x)
    {
        if (x==null)
        {
            return false;
        }
        if (this.root==null)
        {
            this.root=new BinarySortNode(x);
        }
        else
        {
            BinarySortNode<T> p=this.root;
            while (p!=null)
            {
                if (x.compareTo(p.data)==0)
                {
                    return false;
                }
                if (x.compareTo(p.data)<0)
                {
                    p=p.left;
                }
                else
                {
                    p=p.right;
                }
            }
        }
        return true;
    }
}
