package math_thaed;

public class NumberThread extends Thread         //线程类，输出奇数/偶数序列；继承Thread类
{
    private int first,n;                         //序列初值和元素个数

    //构造方法，name指定线程名，first、n指定序列初值和和元素个数
    public NumberThread(String name, int first, int n)
    {
        super(name);                             //构造线程对象时指定线程名
        this.first = first;
        this.n = n;
    }
    public void run()                            //线程运行方法，覆盖Thread的run()
    {
        long time1=System.currentTimeMillis();   //开始时间
        System.out.print("\n"+this.getName()+"开始时间="+time1+"，");//输出线程名和时间
        for(int i=0; i<n; i++)                   //循环输出n个序列值，步长为2
            System.out.print((first+2*i)+"  ");
        long time2=System.currentTimeMillis();   //结束时间
        System.out.println("\n"+this.getName()+"结束时间="+time2+"，耗时"+(time2-time1)+"毫秒。");
    }
    public static void main(String args[])
    {
        Thread thread = Thread.currentThread();            //当前运行线程是main
        System.out.println("currentThread="+thread.getName());     //输出当前线程对象
        System.out.println("main Priority="+thread.getPriority()); //输出当前线程对象的优先级
        Thread thread_odd = new NumberThread("奇数线程",1,10);  //创建线程对象，父类对象引用子类实例
        Thread thread_even = new NumberThread("偶数线程",2,10);
//        thread_odd.setPriority(MIN_PRIORITY);//MAX_PRIORITY);//10);              //设置优先级为最高
        thread_odd.start();                                      //启动线程对象
        thread_even.start();
        System.out.println("activeCount="+Thread.activeCount()); //输出当前活动线程数
//        System.out.println("thread_odd Priority="+thread_odd.getPriority()); //输出当前线程对象的优先级
    }
}
