package wrr;

public class Semaphore{
    private int semValue;
    //定义信号量
    public Semaphore(int semValue) {
        this.semValue = semValue;
    }
    public synchronized void p() {
        semValue--;
        if (semValue < 0) {
            try {
                this.wait();//阻塞该进程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void v(){
        semValue++;
        if (semValue <= 0) {
            this.notify();//唤醒被阻塞的进程
        }
    }
}

