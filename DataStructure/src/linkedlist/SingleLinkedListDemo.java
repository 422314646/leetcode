package linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "小白", "xiaobai");
        HeroNode heroNode2 = new HeroNode(2, "小红", "xiaohong");
        HeroNode heroNode3 = new HeroNode(3, "小蓝", "xiaolan");
        HeroNode heroNode4 = new HeroNode(4, "小绿", "xiaolv");
        HeroNode heroNode5 = new HeroNode(3, "小黑", "xiaohei");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        singleLinkedList.list();
        System.out.println("=====================================");
        singleLinkedList1.addByOrder(heroNode2);
        singleLinkedList1.addByOrder(heroNode1);
        singleLinkedList1.addByOrder(heroNode4);
        singleLinkedList1.addByOrder(heroNode3);
        singleLinkedList1.list();
        System.out.println("=====================================");
        singleLinkedList1.del(3);
        singleLinkedList1.update(heroNode5);
        singleLinkedList1.del(3);
        singleLinkedList1.list();
    }
}

//定义SingleLinkedList管理我们英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //思路不考虑顺序问题
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
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
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
    //如果该编号添加失败，并返回相应信息
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动所以需要一个辅助指针来帮忙找相应位置
        //因为是单链表因此我们需要找到temp是位于添加位置的前一个节点，否则添加不了
        HeroNode temp = head;
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
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode){
        if (head.next == null){
            System.out.println("链表为空！！");
        }
        boolean flag = false;
        HeroNode temp = head.next;
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

    //删除节点
    //1.head不能动，因此我们需要一个temp辅助节点找到删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要的删除的节点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                //没有找到要删除的节点
                break;
            }else if (temp.next.no == no){
                //找到待删除的节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("该节点不存在%d,删除失败\n",no);
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
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}


//定义HeroNde，每一个HeroNode，对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname){
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