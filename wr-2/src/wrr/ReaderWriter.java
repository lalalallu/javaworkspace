package wrr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReaderWriter
{
    public static void main(String[] args)
    {
        Semaphore wmutex = new Semaphore(1);
        Semaphore rmutex = new Semaphore(1);
        int i;
        String s=null;
        My m[]=new My[10];
        try{
            BufferedReader br=new BufferedReader(new FileReader("Input.txt"));
            for (i=0;(s=br.readLine())!=null;i++)
            {
                m[i]=new My(s);
                System.out.println("线程"+m[i].thread+"是"+m[i].type+"线程,第"+m[i].arrive_time+"秒申请读写操作,操作持续"+m[i].operate_time+"秒");
                if (m[i].type.equals("R")){
                    new Thread(new Reader(rmutex,wmutex,m[i])).start();
                }
                else {
                    new Thread(new Writer(wmutex,m[i])).start();
                }
            }
        }
        catch(IOException e){}
    }
}
