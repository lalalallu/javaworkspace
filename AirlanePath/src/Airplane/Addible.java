package Airplane;

public interface Addible<T>                      //可相加接口，T表示数据元素的数据类型
{
    public void add(T tobj);                     //+=相加，约定两元素相加规则
    public boolean removable();                  //约定删除元素的条件
}
