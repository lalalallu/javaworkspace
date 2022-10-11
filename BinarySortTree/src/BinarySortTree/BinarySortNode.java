package BinarySortTree;

public class BinarySortNode<T>                             // 单链表结点类，T指定结点的元素类型
{
    public T data;                               //数据域
    public BinarySortNode<T> left, right;            //左、右孩子结点
    public BinarySortNode(T data, BinarySortNode<T> left, BinarySortNode<T> right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinarySortNode(T data)                    //构造元素为data的叶子结点
    {
        this(data, null, null);
    }
    public String toString()                     //返回结点数据域的描述字符串
    {
        return this.data.toString();
    }

    public boolean isLeaf()                      //判断是否叶子结点
    {
        return this.left==null && this.right==null;
    }
}