package armshift;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Armshift
{
    private int arm;//模拟移动臂
    private int distance = 0;//保存移动臂移动距离
    private int surfaceNum = 0;//保存柱面数
    //生成队列保存输入序列
    LinkedList<Integer> intputQueue(){
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> inputQueue = new LinkedList<Integer>();
        int temp = -1;
        temp = in.nextInt();
        while(temp != -1) {
            inputQueue.offer(temp);
            temp = in.nextInt();
        }
        return inputQueue;
    }
    //模拟移动臂移动
    void move(int address) {
        for(int i = 0;i < Math.abs(address - arm);i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.print("到达柱面" + address + " -> ");
        distance += Math.abs(address - arm);
        arm = address;
    }
    //输出访问序列
    void showOutputQueue(LinkedList<Integer> outputQueue) {
        System.out.print("柱面响应序列为：");
        while(!outputQueue.isEmpty()) {
            System.out.print(outputQueue.poll() + " ");
        }
        System.out.println();
        System.out.println("总移动柱面个数：" + distance + "\n");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
    //FIFO算法
    LinkedList<Integer> FIFO(LinkedList<Integer> queue1){
        LinkedList<Integer> outputQueue = new LinkedList<Integer>();
        while(!queue1.isEmpty()) {
            int popNum = queue1.poll();
            outputQueue.offer(popNum);
            move(popNum);
        }
        System.out.println("");
        return outputQueue;
    }
    void execFiFO(LinkedList<Integer> InputQueue) {
        showOutputQueue( FIFO(InputQueue));
    }
    //单向扫描算法
    void OneWayScan(LinkedList<Integer> queue1,LinkedList<Integer> outputQueueL,LinkedList<Integer> outputQueueR){
        int[] array = toOrderIntArray(queue1);
        boolean firstAdd = true;
        int firstAddr = 0;
        for(int i = 0;i < array.length;i++) {
            if(array[i] >= arm) {
                if(firstAdd) {
                    firstAdd = false;
                    firstAddr = i;
                }
                outputQueueL.offer(array[i]);
                move(array[i]);
            }
        }
        move(surfaceNum);
        move(0);
        for(int i = 0;i < firstAddr;i++) {
            outputQueueL.offer(array[i]);
            move(array[i]);
        }
        System.out.println();
        showOutputQueue(outputQueueL);
    }
    //把输入队列转换成有序数组
    int[] toOrderIntArray(LinkedList<Integer> InputQueue) {
        int[] array = new int[InputQueue.size()];
        Iterator<Integer> it = InputQueue.iterator();
        for(int i = 0;i < array.length;i++) {
            array[i] = it.next();
        }
        return insert(array);
    }
    //插入排序算法
    int[] insert(int[] array) {
        int len = array.length;
        int temp;
        for(int i = 1;i < len;i++) {
            temp = array[i];
            int j;
            for(j = i - 1;j >= 0;j--) {
                if(temp < array[j]) {
                    array[j+1] = array[j];
                }
                else
                    break;
            }
            array[j + 1] = temp;
        }
        return array;
    }
    void execOneWay(LinkedList<Integer> InputQueue) {
        distance = 0;
        LinkedList<Integer> OutputLeftQueue = new LinkedList<Integer>();
        LinkedList<Integer> OutputRightQueue = new LinkedList<Integer>();
        OneWayScan(InputQueue,OutputLeftQueue,OutputRightQueue);
    }
    void menu() {
        LinkedList<Integer> intputQueue;
        int selectFunction;
        do {
            System.out.print("请你输入请求访问的柱面序列(以-1作为结束标志)：");
            intputQueue = intputQueue();
            System.out.print("请输入移动臂的初始位置:");
            arm = (new Scanner(System.in)).nextInt();
            System.out.print("请输入最里的柱面号:");
            surfaceNum = (new Scanner(System.in)).nextInt();
            System.out.println("\n请你选择算法");
            System.out.println("1.先来先服务算法");
            System.out.println("2.单向扫描调度算法");
            System.out.println("0.退出");
            System.out.println("");
            System.out.print("请选择：");
            selectFunction = (new Scanner(System.in)).nextInt();
            distance = 0;
            switch (selectFunction) {
                case 1:
                    System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    System.out.println("\n\t\t先来先服务算法\n");
                    execFiFO(intputQueue);
                    break;
                case 2:
                    System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    System.out.println("\n\t\t单向扫描调度算法\n");
                    execOneWay(intputQueue);
                    break;
                default:
                    System.out.println("\n已退出！");
                    break;
            }
        } while (selectFunction != 0);
    }

}
class runTest {
    public static void main(String[] args) {
        (new Armshift()).menu();
    }
}
