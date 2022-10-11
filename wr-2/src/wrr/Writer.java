package wrr;

class Writer implements Runnable{
    private Semaphore wmutex;
    My my;
    public Writer(Semaphore wmutex,My my){
        this.wmutex = wmutex;
        this.my = my;
    }
    @Override
    public void run() {
        try{
            Thread.sleep((int)(1000*my.arrive_time));
        }catch (InterruptedException e){e.printStackTrace();}
        System.out.println("线程" + my.thread + "申请写操作");
        wmutex.p();
        System.out.println("线程" + my.thread + "开始写操作");
        try{
            Thread.sleep((int)(1000*my.operate_time));
        }catch (InterruptedException e){e.printStackTrace();}
        wmutex.v();
        System.out.println("线程" + my.thread + "完成写操作");
    }
}

