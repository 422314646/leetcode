package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        char key = ' ';//接受用户输入
        System.out.println("数组队列模拟环形队列");
        //设置为4，其有效的空间为3
        CircleArray arrayQueue = new CircleArray(4);
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

class CircleArray{
    private int maxSize; //表示数组最大容量
    //1.front变量的含义做一个调整：front就指向队列的第一个元素，也就是arr[front]就是队列的第一个元素
    //front初始值=0
    private int front; //表示队列头部
    //2.rear变量的：rear指向最后一元素的后一个位置，因为希望空出一个空间做为约定。
    //rear初始值=0
    private int rear; //表示队列尾部
    private int[] arr; //该数据用于存放数据，模拟队列

    public CircleArray(int maxSize){
        //front初始值=0
        //rear初始值=0
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否为满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        //数据直接存入
        arr[rear] = n;
        //rear在最后一个元素的后一位，所以需要进行取模。（防止越界）
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            //通过抛异常
            throw new RuntimeException("队列为空！！");
        }
        //front指向数组的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时变量返回
        int flag = arr[front];
        front = (front + 1) % maxSize;
        return flag;
    }

    //展示所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空！！");
            return;
        }
        //思路从front开始，遍历所烧个数据
        for (int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=[%d]\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前有效元素个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！！");
        }
        return arr[front];
    }
}
