package queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {

        //测试队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 取出队列数据");
            System.out.println("h(head): 显示队列头数据");
            key = scanner.next().charAt(0);//接受第一字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int i = scanner.nextInt();
                    arrayQueue.addQueue(i);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据：%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头：%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

//使用数组模拟队列，编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize; //表示数组最大容量
    private int front; //表示队列头部
    private int rear; //表示队列尾部
    private int[] arr; //该数据用于存放数据，模拟队列

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部，分析出front是指向队列的前一个位置
        rear = -1; //只想队列尾部，指向队列尾的数据（即就是队列最后一个数据）
    }

    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满！！");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            //通过抛异常
            throw new RuntimeException("队列为空！！");
        }
        front++;
        return arr[front];
    }

    //展示所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空！！");
            return;
        }
        for (int i = 0; i < arr.length; i++){
            System.out.printf("arr[%d]=[%d]\n", i, arr[i]);
        }
    }

    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！！");
        }
        return arr[front+1];
    }
}
