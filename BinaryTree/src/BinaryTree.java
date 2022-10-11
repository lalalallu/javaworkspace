import BinaryNode.BinaryNode;
import BinaryNode.LinkQueue;
import LinkedStack.LinkedStack;

public class BinaryTree<T>
{
    public BinaryNode<T> root;                   //根结点，二叉链表结点结构
    public BinaryTree()                          //构造空二叉树
    {
        this.root=null;
    }
    public BinaryTree(T[] prelist)               //以标明空子树的先根次序遍历序列构造二叉树
    {
        this.root = create(prelist);
    }
    private int j=0;
    private BinaryNode<T> create(T[] prelist)
    {
        BinaryNode<T> p = null;
        if(j<prelist.length)
        {
            T elem=prelist[j++];
            if(elem!=null)                       //不能elem!="∧"，因为T不一定是String
            {
                p = new BinaryNode<T>(elem);     //创建叶子结点
                p.left = create(prelist);        //创建p的左子树，递归调用，实际参数与形式参数相同
                p.right = create(prelist);       //创建p的右子树，递归调用，实际参数与形式参数相同
            }
        }
        return p;
    }
    public boolean isEmpty()                     //判断是否空二叉树
    {
        return this.root==null;
    }
    public void insert(T x)            //插入x元素作为根结点，x!=null，原根结点作为x的左孩子
    {
        if(x!=null) {
            this.root = new BinaryNode<T>(x, this.root, null);
        }
    }
    public BinaryNode<T> insert(BinaryNode<T> p, boolean left, T x)
    {
        if(x==null || p==null) {
            return null;
        }
        if(left)                                 //插入x为p结点的左/右孩子，返回插入结点
        {
            return p.left = new BinaryNode<T>(x, p.left, null);
        }
        return p.right = new BinaryNode<T>(x, null, p.right);
    }

    static<T> BinaryTree<T> createComplete(T[] levellist)  //以层次遍历序列构造完全二叉树
    {
        if (levellist==null)
        {
            return null;
        }
        BinaryTree<T> tem=new BinaryTree<>();
        tem.root=create(levellist,0);
        return tem;
    }

    private static <T> BinaryNode<T> create(T[] levellist, int i)
    {
        BinaryNode<T> p = null;
        if(i<levellist.length)
        {
            T element = levellist[i];
            p = new BinaryNode<>(element);
            p.left = create(levellist, 2 * i + 1);
            p.right = create(levellist, 2 * i + 2);
        }
        return p;
    }


//    6-9  【课程设计题6-2】判断是否为完全二叉树。
//BinaryTree(T[] prelist)                            	//以标明空子树的先根次序遍历序列构造二叉树，使用栈
//static<T> BinaryTree<T> createComplete(T[] levellist)  	//返回以层次遍历序列构造的完全二叉树，使用队列
//static<T> boolean isComplete(BinaryTree<T> bitree)    	//判断一棵二叉树是否为完全二叉树，使用队列

    static<T> BinaryTree<T> createBinaryTree (T[] prelist)
    {
        LinkedStack<BinaryNode<T>> stack=new LinkedStack();
        BinaryNode<T> p;
        BinaryTree<T> b=new BinaryTree<T>();
        int i=0;
        b.root=new BinaryNode<T>(prelist[i++]);
        p=b.root;
        while (p!=null||!stack.isEmpty())
        {
            if (p!=null)
            {
                stack.push(p);
                if (prelist[i]!=null)
                {
                    p.left=new BinaryNode<T>(prelist[i++]);
                    p=p.left;
                }
                else
                {
                    p.left=null;
                    p=p.left;
                    i++;
                }
            }
            else
            {
                p=stack.pop();
                if (prelist[i]!=null)
                {
                    p.right = new BinaryNode<T>(prelist[i++]);
                    p = p.right;
                    //stack.push(p);
                }
                else
                {
                    p.right=null;
                    p=p.right;
                    i++;
                }
            }
        }
        return b;
    }



    static<T> BinaryTree<T> createComplete3(T[] levellist)
    {
        if (levellist==null)
        {
            return null;
        }
        int i=0;
        LinkQueue<BinaryNode<T>> que=new LinkQueue<BinaryNode<T>>();
        BinaryTree<T> d1=new BinaryTree<>();
        que.add(d1.root= new BinaryNode<>(levellist[i++]));
        while (i<levellist.length)
        {
            BinaryNode<T> d2= que.poll();
            que.add(d2.left= new BinaryNode<>(levellist[i++]));
            if (i<levellist.length)
            {
                que.add(d2.right = new BinaryNode<>(levellist[i++]));
            }
        }
        return d1;
    }

    static<T> boolean isComplete(BinaryTree<T> bitree)
    {
        if(bitree.root==null||(bitree.root.left==null&&bitree.root.right==null))
        {
            return true;
        }
        LinkQueue<BinaryNode<T>> que=new LinkQueue<BinaryNode<T>>();
        BinaryNode<T> p= bitree.root;
        que.add(p);
        int flag=0;
        while (!que.isEmpty())
        {
            p= que.poll();
            if (p.left!=null)
            {
                if(que.add(p.left)&&flag==1)
                {
                    flag++;
                }
                if (flag==2) {
                    break;
                }
                if (!que.add(p.right))
                {
                    flag++;
                }
            }
            if (p.left==null&&p.right!=null)
            {
                return false;
            }
        }
        if (flag==2) {
            return false;
        }
        return true;
    }

    static<T> boolean isComplete2(BinaryTree<T> bitree)
    {
        if(bitree.root==null||(bitree.root.left==null&&bitree.root.right==null)) {
            return true;
        }
        LinkQueue<BinaryNode<T>> que=new LinkQueue<BinaryNode<T>>();
        BinaryNode<T> p= bitree.root;
        que.add(p);
        while (!que.isEmpty())
        {
            p=que.poll();
            if (p.left==null&&p.right!=null)
            {
                return false;
            }
            if (p.left!=null&&p.right!=null)
            {
                que.add(p.left);
                que.add(p.right);
            }
            else
            {
                que.add(p.left);
                break;
            }
        }
        while (!que.isEmpty())
        {
            p=que.poll();
            if (p.left!=null||p.right!=null)
            {
                return false;
            }
        }
        return true;
    }

    public BinaryTree<T> createComplete2(T[] levellist)        //队列构造层次结构完全二叉树
    {
        if (levellist==null)
        {
            return null;
        }
        int i=0;
        LinkQueue<BinaryNode<T>> que=new LinkQueue<BinaryNode<T>>();
        BinaryTree<T> d1=new BinaryTree<>();
        que.add(d1.root= new BinaryNode<>(levellist[i++]));
        while (i<levellist.length)
        {
            BinaryNode<T> d2= que.poll();
            que.add(d2.left= new BinaryNode<>(levellist[i++]));
            if (i<levellist.length)
            {
                que.add(d2.right = new BinaryNode<>(levellist[i++]));
            }
        }
        return d1;
    }

    public static <T extends Comparable<? super T>>boolean isSorted(BinaryTree<T> tree)//是否排序
    {
        //BinaryNode p=tree.root;
        return  tree.compareSort2(tree.root);
    }


    private static BinaryNode f;


    protected <T extends Comparable<? super T>>boolean compareSort(BinaryNode<T> p)
    {
        if (p==null)
        {
            return true;
        }
        if (compareSort(p.left))
        {
            BinaryNode<T> q=f;
            if (q==null||p.data.compareTo(q.data)>0)
            {
                f=p;
                if (p.right==null)
                {
                    return true;
                }
                return compareSort(p.right);
            }
        }
        return false;
    }

    private static int i=0;
    protected <T extends Comparable<? super T>>boolean compareSort2(BinaryNode<T> p)
    {
        if (p==null)
        {
            return false;
        }
        if (p!=null)
        {
            compareSort2(p.left);
            if (i==0)
            {
                i=-1;
                f=p;
            }
            BinaryNode<T> q=f;
            if (p.data.compareTo(q.data)<0)
            {
                //return false;
                i=2;
            }
            f=p;
            compareSort2(p.right);
        }
        if (i==2)
        {
            return false;
        }
        return true;
    }

    public String toGenListString()              //返回二叉树的广义表表示字符串
    {
        return "二叉树的广义表表示："+toGenListString(this.root);
    }

    public String toGenListString(BinaryNode<T> p)
    {
        if(p==null) {
            return "∧";                          //返回空子树标记
        }
        if(p.left==null && p.right==null)         //p是叶子结点(p.isLeaf())
        {
            return p.data.toString();
        }
        return p.data.toString()+"("+toGenListString(p.left)+","+toGenListString(p.right)+")";
    }

    BinaryTree(BinaryTree<T> bitree)//深拷贝
    {
        this.root=copy(bitree.root);
    }
    private BinaryNode<T> copy(BinaryNode<T> p)
    {
        //BinaryNode<T> p=bitree;
        BinaryNode<T> q=null;
        if (p!=null)
        {
            q= new BinaryNode<>(p.data);
            q.left=copy(p.left);
            q.right=copy(p.right);
        }
        return q;
    }
}
