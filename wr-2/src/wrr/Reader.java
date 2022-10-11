package wrr;

class Reader implements Runnable{
    private Semaphore rmutex,wmutex;
    My my;
    static int readcount = 0;
    public Reader(Semaphore rmutex,Semaphore wmutex,My my){
        this.rmutex = rmutex;
        this.wmutex = wmutex;
        this.my = my;
    }
    @Override
    public void run() {
        try{
            Thread.sleep((int)(1000*my.arrive_time));
        }catch (InterruptedException e){e.printStackTrace();}
        System.out.println("线程" + my.thread + "申请读操作");
        rmutex.p();
        if (readcount == 0) wmutex.p();
        readcount++;
        rmutex.v();
        System.out.println("线程" + my.thread + "开始读操作");
        try{
            Thread.sleep((int)(1000*my.operate_time));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        rmutex.p();
        readcount--;
        System.out.println("线程" + my.thread + "结束读操作");
        if (readcount == 0) wmutex.v();
        rmutex.v();
    }
}

