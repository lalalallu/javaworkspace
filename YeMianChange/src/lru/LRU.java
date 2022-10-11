package lru;

import java.util.ArrayList;
import java.util.List;

public class LRU
{
    public int  search(List list, int k)
    {
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).equals(k))
            {
                return i;
            }
        }
        return -1;
    }
    public void run()
    {
        Integer[] inputInt = {6, 7, 6, 5, 9, 6, 8, 9, 7, 6, 9, 6};
        // 内存块
        int memory = 4;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < inputInt.length; i++)
        {
            if(i == 0)
            {
                list.add(inputInt[i]);
                System.out.println("第"+ i +"次序列： " + list);
            }
            else
            {
                if(search(list,inputInt[i])!=-1)
                {
                    // 存在字符串，则获取该下标
                    int index = search(list,inputInt[i]);

                    // 下标不位于栈顶时，且list大小不为1时
                    if(!(list.get(list.size() - 1)).equals(inputInt[i]) && list.size() != 1)
                    {
                        int k = list.get(index);
                        list.remove(index);
                        list.add(k);
                    }
                    System.out.println("第" + i + "次" + "序列： " +list);
                }
                else
                {
                    if(list.size()>= memory)
                    {
                        list.remove(0);
                        list.add(inputInt[i]);
                        System.out.println("第" + i + "次" + "序列： " + list);
                    }
                    else
                    {
                        list.add(inputInt[i]);
                        System.out.println("第" + i + "次" + "序列： " + list);
                    }
                }
            }
        }
    }

}
class Main
{
    public static void main(String[] args) {
        LRU lru=new LRU();
        lru.run();
    }
}