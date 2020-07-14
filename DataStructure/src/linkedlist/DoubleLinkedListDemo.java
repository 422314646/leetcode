package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "小白", "xiaobai");
        HeroNode2 heroNode2 = new HeroNode2(2, "小红", "xiaohong");
        HeroNode2 heroNode3 = new HeroNode2(3, "小蓝", "xiaolan");
        HeroNode2 heroNode4 = new HeroNode2(4, "小绿", "xiaolv");
        HeroNode2 heroNode5 = new HeroNode2(5, "小黑", "xiaohei");
        HeroNode2 heroNode6 = new HeroNode2(3, "小城", "xiaohei");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode5);
        doubleLinkedList.list();
        doubleLinkedList.update(heroNode6);
        System.out.println("==============================");
        doubleLinkedList.list();
        System.out.println("==============================");
        doubleLinkedList.del(5);
        doubleLinkedList.list();
        System.out.println("==============================");
        doubleLinkedList.addByOrder(heroNode5);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0," "," ");

    public HeroNode2 getHead() {
        return head;
    }

    //按编号顺序添加
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动所以需要一个辅助指针来帮忙找相应位置
        HeroNode2 temp = head;
        boolean flag = false;//标志位，是否存在重复的，默认false
        while (true){
            if (temp.next == null){//说明temp是最后一个节点
                break;
            }else if (temp.next.no > heroNode.no){//找到位置 就在temp后面
                break;
            }else if (temp.next.no == heroNode.no){//说明已经存在了
                flag = true;
                break;
            }
            temp = temp.next;//遍历链表
        }
        if (flag){
            System.out.printf("该编号%d已经存在，添加失败\n", heroNode.no);
        }else {
            if (temp.next != null){
                heroNode.pre = temp;
                heroNode.next = temp.next;
                temp.next = heroNode;
                temp.next.pre = heroNode;
            }else {
                heroNode.pre = temp;
                temp.next = heroNode;
            }

        }
    }

    //删除节点
    //1.head不能动，因此我们需要一个temp辅助节点找到删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要的删除的节点的no比较
    public void del(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                //没有找到要删除的节点
                break;
            }else if (temp.no == no){
                //找到待删除的节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面的语句，否则会出现空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("该节点不存在%d,删除失败\n",no);
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode2 newHeroNode){
        if (head.next == null){
            System.out.println("链表为空！！");
        }
        boolean flag = false;
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){//遍历完所有节点
                break;
            }else if(temp.no == newHeroNode.no){//找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {//没有找到
            System.out.printf("没有找到%d的节点，修改失败\n",newHeroNode.no);
        }
    }

    //显示链表[遍历]
    public void list(){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量进行遍历
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加节点在最后面
    public void add(HeroNode2 heroNode){
        //因为head头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，则指向下一跳
            temp = temp.next;
        }
        //当退出循环时，temp数据指向链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }
}

//定义HeroNde，每一个HeroNode，对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    public HeroNode2(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}