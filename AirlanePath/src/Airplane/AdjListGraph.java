package Airplane;

//邻接表存储的带权图类，T表示顶点元素类型；继承抽象图类
public class AdjListGraph<T> extends AbstractGraph<T>
{
    protected LinkedMatrix linkmat;              //图的邻接表，矩阵行的单链表

    public AdjListGraph()                        //构造空图，顶点数为0，边数为0
    {
        super();                                 //构造空顶点顺序表，默认容量
        this.linkmat = new LinkedMatrix(0,0);    //构造0×0矩阵，默认容量
    }
    public AdjListGraph(T[] vertexes)            //以vertexes顶点集合构造图，边集合为空
    {
        this();
        for(int i=0;  i<vertexes.length;  i++)
            this.insert(vertexes[i]);            //插入顶点，稍后说明算法
    }
    public AdjListGraph(T[] vertexes, Triple[] edges)//以vertexes顶点集合和edges边集合构造图
    {
        this(vertexes);                          //以vertexes顶点集合构造图，没有边
        for(int j=0;  j<edges.length;  j++)
            this.insert(edges[j]);               //插入边，稍后说明算法
    }

    //以vertexes顶点集合和edges边集合构造图，edges字符串指定边集合，“,”分隔，没有空格
    public AdjListGraph(T[] vertexes, String edges)
    {
        this(vertexes);                          //以vertexes顶点集合构造图，没有边
        this.linkmat = new LinkedMatrix(vertexes.length, vertexes.length, edges);//构造n×n矩阵
    }

    public String toString()                     //返回图的顶点集合和邻接表描述字符串
    {
        return super.toString()+"出边表：\n"+this.linkmat.toString();
    }

    //（2）插入顶点
    public int insert(T x)                       //插入元素为x的顶点，返回x顶点序号
    {
        this.vertexlist.insert(x);               //顶点顺序表尾插入x，自动扩容
        int i = this.vertexlist.size()-1;        //获得插入顶点x的序号
        if(i >= this.linkmat.getRows())          //若邻接表容量不够
            this.linkmat.setRowsColumns(i+1, i+1);//则扩容，保持邻接表行数同图的顶点数
        return i;                                //返回插入顶点序号
    }

    //（3）插入边
    public void insert(int i, int j, int w)      //插入边〈vi,vj〉，权值为w
    {
        if(i!=j)                                 //不能表示自身环
        {
            if(w<0 || w>=MAX_WEIGHT)             //边的权值容错，视为无边，取值0
                w=0;
            this.linkmat.set(i, j, w);           //设置第i条边单链表中〈vi,vj〉边的权值为w。
            //若0<w<∞，插入边或替换边的权值；若w==0，删除该边。若i、j越界，抛出序号越界异常
        }
        else
            throw new IllegalArgumentException("不能插入自身环，i="+i+"，j="+j);
    }
    public void insert(Triple edge)              //插入一条边。方法体同图的邻接矩阵，省略
    {
        this.insert(edge.row, edge.column, edge.value);
    }

    //（4）删除边
    public void remove(int i, int j)             //删除一条边〈vi,vj〉，忽略权值
    {
        if(i!=j)
            this.linkmat.set(new Triple(i,j,0)); //设置边的权值为0，即在第i条边单链表中删除边结点
    }
    public void remove(Triple edge)              //删除一条边。方法体同图的邻接矩阵，省略
    {
        this.remove(edge.row, edge.column);
    }

    //（5）删除顶点
    public T remove(int i)          //删除顶点vi及其所有关联的边，返回顶点vi元素。用最后一个顶点替换顶点vi
    {
        int n=this.vertexCount();                //图的顶点数
        if(i>=0 && i<n)
        {
            //① 顶点顺序表删除第i个顶点，用最后一个顶点替换顶点vi
            T x = this.vertexlist.get(n-1);
            this.vertexlist.set(i, x);
            x = this.vertexlist.remove(n-1);

            //② 删除所有与顶点vi相关联的边。
            //<I> 删除与第i条边单链表中每条边对称的边结点
            SortedSinglyList<Triple> link = this.linkmat.rowlist.get(i);
            for(Node<Triple> p=link.head.next;  p!=null;  p=p.next)  //遍历第i条边单链表
                this.remove(p.data.toSymmetry());//删除与p边结点对称的边

            //<II> 将最后一条边单链表中每条边及其对称边的顶点序号n-1改为i
            link = this.linkmat.rowlist.get(n-1);
            for(Node<Triple> p=link.head.next;  p!=null;  p=p.next)
            {
                Triple edge = p.data.toSymmetry();    //与p边结点对称的边
                this.remove(edge);               //删除边
                edge.column = i;                 //边的终点序号改为i
                this.insert(edge);               //再插入边，为了排序
                p.data.row = i;                  //p边结点，边的起点序号改为i
            }

            //<III> 在行指针顺序表中，将第i条边单链表替换为最后一条（n-1），再删除最后一条。
            this.linkmat.rowlist.set(i, this.linkmat.rowlist.get(n-1));
            this.linkmat.rowlist.remove(n-1);
            this.linkmat.setRowsColumns(n-1, n-1);//设置矩阵行列数，少一行
            return x;
        }
        else
            throw new IndexOutOfBoundsException("i="+i);  //抛出序号越界异常
    }
    //@author：Yeheya，2016-2-7，除夕

    //（5） 获得邻接顶点和边的权值属性
    public int weight(int i, int j)              //返回<vi,vj>边的权值。用于图的最小生成树、最短路径等算法
    {
        if(i==j)
            return 0;
        int w = this.linkmat.get(i,j);           //返回矩阵元素[i,j]值。若i、j越界，抛出序号越界异常
        return w!=0 ? w : MAX_WEIGHT;            //若返回0表示没有边，则边的权值返回∞
    }

    //返回顶点vi在vj后的后继邻接顶点序号；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。用于7.3节图的遍历算法
    protected int next(int i, int j)
    {
        int n=this.vertexCount();
        if(i>=0 && i<n && j>=-1 && j<n && i!=j)
        {
            SortedSinglyList<Triple> link = this.linkmat.rowlist.get(i);//第i条排序单链表
            Node<Triple> find=link.head.next;              //单链表第0个元素
            if(j==-1)
                return find!=null ? find.data.column : -1; //返回第一个邻接顶点的序号
            find = link.search(new Triple(i,j,0));         //顺序查找<vi,vj>边的结点
            if(find!=null && (find=find.next)!=null)       //find引用<vi,vj>边的后继结点
                return find.data.column;                   //返回后继邻接顶点序号
        }
        return -1;
    }
}
/*
    //第10章，10.2 实现迭代器
    //10.2.2   基于迭代器的操作
    //【思考题10-4】
    public void removeVertex(int i)              //删除顶点vi及其关联的边。使用单链表迭代器遍历，没有用到删除
    {
        int n=this.vertexCount();                          //删除之前的顶点数
        if (i>=0 && i<n)
        {
            //删除与第i条边单链表中所有结点对称的边，即在第i条以外的边单链表中，删除所有以i为终点的边
            SortedSinglyList<Triple> link = this.linkmat.rowlist.get(i);//获得第i行排序单链表
            java.util.Iterator<Triple> it = link.iterator();//获得单链表迭代器对象
            while (it.hasNext())                           //遍历第i条边单链表
                this.removeEdge(it.next().toSymmetry());   //删除与p结点对称的边

            n--;                                           //顶点数减1
            this.linkmat.rowlist.remove(i);                //删除行指针顺序表的第i条边单链表，其后单链表上移
            this.linkmat.setRowsColumns(n, n);             //设置矩阵行列数，少一行

            for (int j=0; j<n; j++)                        //遍历每条边单链表，将>i的顶点序号减1
            {
                link = this.linkmat.rowlist.get(j);
//                for (Node<Triple> p=link.head.next; p!=null; p=p.next)//遍历第j条边单链表
                it = link.iterator();                      //获得第i行排序单链表迭代器对象
                while (it.hasNext())                       //遍历第i条边单链表
                {
                    Triple tri= it.next();
                    if (tri.row > i)
                        tri.row--;
                    if (tri.column > i)
                        tri.column--;
                }
            }
            this.vertexlist.remove(i);                     //删除顶点vi，i后顶点序号减1，图顶点数减1
        }
        else throw new IndexOutOfBoundsException("i="+i);  //抛出序号越界异常
    }
}*/
