package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Josephu {
    public static void main(String[] args) {
        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建第一个节点，但是没有值
    private Boy first = new Boy(-1);
    //添加节点，构成一个环状链表
    public void addBoy(int nums){
        //nums做一个数据校验
        if(nums < 1){
            System.out.println("nums太小，不行");
            return;
        }
        Boy cur = null;//辅助指针，帮助构形环状
        //利用for循环构建环形链表
        for (int i = 1; i <= nums; i++){
            Boy boy = new Boy(i);
            //如果是第一个节点
            if (i == 1){
                first = boy;
                first.setNext(first);
                cur = first;
            }else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        if (first == null){
            System.out.println("当前链表为空！！");
        }
        Boy cur = first;
        while (true){
            System.out.printf("当前节点节点编号:%d\n",cur.getNo());
            if (cur.getNext() == first){//说明遍历完毕
                break;
            }
            cur = cur.getNext();
        }
    }

    //根据用户输入，计算小孩出圈的顺序
    /*
    * @Param startNo 表示从第几个小孩开始
    * @Param countNum 表示数几下
    * @Param nums 一共多少个小孩
    * */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("输入有误");
            return;
        }
        //创建一个辅助指针指向环形链表的最后
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //把helper和first指针同时移动startNo-1次（first移动到开始报数位置）
        for (int i = 0; i < startNo - 1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //循环操作，当只有一个节点时说明已经知道出圈顺序了
        while (true){
            if (helper == first){
                break;
            }
            //让first和helper移动countNum-1次
            for (int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("编号%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("编号%d在圈里面\n",first.getNo());
    }
}

//创建一个Boy类，表示一个节点、
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy(int no){
        this.no =no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}