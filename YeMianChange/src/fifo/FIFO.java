package fifo;

import java.util.ArrayList;
import java.util.List;

public class FIFO
{
    public int  search(List list,int k)
    {
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).equals(k))
            {
                return 0;
            }
        }
        return 1;
    }
    public void run()
    {
        Integer[] inputInt = {6,7,6,5,9,6,8,9,7,6,9,6};

        int memory = 3;// 内存块
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < inputInt.length; i++) {
            if (i == 0) {
                list.add(inputInt[i]);
                System.out.println("第" + i + "次序列： " + list.toString());
            }
            else
            {
                if (search(list,inputInt[i])==0)
                {
                    System.out.println("第" + i + "次" + "序列： " + list.toString());
                }
                else
                {
                    if (list.size() < memory)
                    {
                        list.add(inputInt[i]);
                    }
                    else
                    {
                        list.remove(0);
                        list.add(inputInt[i]);
                    }
                    System.out.println("第" + i + "次" + "序列： " + list.toString());
                }
            }
        }
    }
}

class Main
{
    public static void main(String[] args) {
        FIFO fifo=new FIFO();
        fifo.run();
    }
}