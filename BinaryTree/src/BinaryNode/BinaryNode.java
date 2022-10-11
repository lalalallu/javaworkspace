package BinaryNode;

public class BinaryNode<T>
{
    public T data;                               //数据域
    public BinaryNode<T> left, right;            //左、右孩子结点
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinaryNode(T data)                    //构造元素为data的叶子结点
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
