package DoubleNode;

public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> prev, next;

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DoubleNode() {
        this(null, null, null);
    }

    public String toString() {
        return this.data.toString();
    }

    public boolean equals(Object obj)            //比较两个结点值是否相等，覆盖Object类的equals(obj)方法
    {
        return obj == this || obj instanceof DoubleNode<?> && this.data == (((DoubleNode<T>) obj).data);
    }
}
