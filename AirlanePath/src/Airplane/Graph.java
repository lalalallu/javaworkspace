package Airplane;

//《数据结构与算法（Java版）（第5版）》，作者：叶核亚，2019年8月11日
//§7.1.2   图抽象数据类型
//图接口

public interface Graph<T>                        //图接口，表示图抽象数据类型，T指定顶点元素类型
{
    int vertexCount();                           //返回顶点数
    T get(int i);                                //返回顶点vi元素
    void set(int i, T x);                        //设置顶点vi元素为x
    int insert(T x);                             //插入元素值为x的顶点，返回顶点序号
    void insert(int i, int j, int w);            //插入边〈vi,vj〉，权值为w
    T remove(int i);                             //删除顶点vi及其关联的边
    void remove(int i, int j);                   //删除边〈vi,vj〉
    int search(T key);                           //查找并返回首个与key相等元素的顶点序号
    T remove(T key);                             //查找并删除首个与key相等元素顶点及其关联的边
    int weight(int i, int j);                    //返回〈vi,vj〉边的权值

    void DFSTraverse(int i);                     //图的深度优先遍历，从顶点vi出发
    void BFSTraverse(int i);                     //图的广度优先遍历，从顶点vi出发
    void minSpanTree();                          //构造带权无向图的最小生成树，Prim算法
    void shortestPath(int i);                    //求带权图顶点vi的单源最短路径，Dijkstra算法
    void shortestPath();                         //求带权图每对顶点间的最短路径及长度，Floyd算法
}
//@author：Yeheya。2015年11月15日，2019年8月11日
