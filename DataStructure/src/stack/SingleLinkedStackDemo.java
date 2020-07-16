package stack;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedStackDemo {

    public static void main(String[] args) {
        //测试
        SingleListStack singleListStack = new SingleListStack(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        String key = "";
        while (loop){
            System.out.println("show:展示栈");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key){
                case "show":
                    singleListStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    singleListStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = singleListStack.pop();
                        System.out.printf("出栈的数字为：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//创建一个链表栈
class SingleListStack{
    //创建一个头节点
    private SingleList head = new SingleList(0);
    //栈的大小
    private int maxSize;

    public SingleListStack(int maxSize){
        this.maxSize = maxSize;
    }

    //判断栈满
    public boolean isFull(){
        int flag = 0;
        SingleList cur = head.getNext();
        while (cur != null){
            flag++;
            cur = cur.getNext();
        }
        return flag == maxSize;
    }

    //判断栈空
    public boolean isEmpty(){
        return head.getNext() == null;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满不能入栈");
            return;
        }
        SingleList helper = head;
        while (helper.getNext() != null){
            helper = helper.getNext();
        }
        SingleList newSingleList = new SingleList(value);
        helper.setNext(newSingleList);
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，不能出栈");
        }
        SingleList helper = head;
        while (helper.getNext().getNext() != null){
            helper = helper.getNext();
        }
        int value = helper.getNext().getData();
        helper.setNext(helper.getNext().getNext());
        return value;
    }

    //遍历   这样写不好，运用了栈。。。。。
    public void list(){

        if (isEmpty()){
            System.out.println("栈空！！");
            return;
        }
        Stack<Integer> stack = new Stack<Integer>();
        SingleList helper = head.getNext();
        while (helper != null){
            stack.push(helper.getData());
            helper = helper.getNext();
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

}

//定义一个节点
class SingleList{
    private int data;
    private SingleList next;

    public SingleList(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SingleList getNext() {
        return next;
    }

    public void setNext(SingleList next) {
        this.next = next;
    }
}